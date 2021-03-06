/*
 * @(#)$Id: BigDocumentTable.java 3881 2008-05-27 23:04:16Z yui $
 *
 * Copyright 2006-2008 Makoto YUI
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Makoto YUI - initial implementation
 */
package xbird.xquery.dm.dtm;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xbird.config.Settings;
import xbird.storage.DbCollection;
import xbird.storage.DbException;
import xbird.storage.io.FixedSegments;
import xbird.util.collections.ints.Int2QCache;
import xbird.util.collections.ints.IntHash;
import xbird.util.collections.ints.IntHash.IntLRUMap;
import xbird.util.collections.longs.ObservableLongLRUMap;
import xbird.util.collections.longs.LongHash.Cleaner;
import xbird.util.concurrent.lock.AtomicBackoffLock;
import xbird.util.concurrent.lock.ILock;
import xbird.util.io.FastMultiByteArrayOutputStream;
import xbird.util.lang.PrivilegedAccessor;
import xbird.util.primitive.Primitives;
import xbird.util.resource.PropertyMap;
import xbird.xquery.XQueryException;
import xbird.xquery.dm.instance.DocumentTableModel;
import xbird.xquery.dm.labeling.LabelingHandler;
import xbird.xquery.misc.BasicStringChunk;
import xbird.xquery.misc.QNameTable;

/**
 * 
 * <DIV lang="en"></DIV>
 * <DIV lang="ja"></DIV>
 * 
 * @author Makoto YUI (yuin405+xbird@gmail.com)
 * @deprecated
 */
public class BigDocumentTable extends AbstractDocumentTable {
    private static final long serialVersionUID = -3235760749002816744L;
    private static final Log LOG = LogFactory.getLog(BigDocumentTable.class);

    public static final String DTM_CLASS = BigDocumentTable.class.getName();

    private static final int DEFAULT_PAGES = 8;
    private static final int PAGE_SHIFT = Integer.parseInt(Settings.get("xbird.database.pageshift", "9"));
    /** Page size: 4K */
    private static final int PAGE_LENGTH = 1 << PAGE_SHIFT;
    private static final int PAGE_SIZE = PAGE_LENGTH * Primitives.LONG_BYTES;
    private static final int PAGE_MASK = PAGE_LENGTH - 1;

    private static final float ENLARGE_PAGES_FACTOR = 1.4f;

    /** (Default) Extent size: 4K * 16 = 64K */
    private static final int DEFAULT_EXTENT_THRESHOLD = Integer.getInteger("xbird.db.segment_threshold", 16);
    private static final int DEFAULT_EXTENT_SIZE = PAGE_SIZE * DEFAULT_EXTENT_THRESHOLD;

    //--------------------------------------------
    // persistent stuff

    protected long[][] _block;

    //--------------------------------------------  

    public BigDocumentTable() {
        super(0L, new QNameTable(32), new BasicStringChunk());
        this._block = new long[DEFAULT_PAGES][];
    }

    protected BigDocumentTable(final DbCollection coll, final String docName, final PropertyMap docProps) {
        super(coll, docName, docProps);
        final String pages = docProps.getProperty(KEY_USED_PAGES + docName);
        final int usedPages = (pages == null) ? DEFAULT_PAGES : Integer.parseInt(pages);
        this._block = new long[usedPages][];
    }

    public BigDocumentTable(final DbCollection coll, final String docName) {
        super(coll, docName, null);
        this._block = new long[DEFAULT_PAGES][];
    }

    public PagingProfile getPagingProfile() {
        return null;
    }

    public long dataAt(final long at) {
        return _block[(int) (at >> PAGE_SHIFT)][(int) (at & PAGE_MASK)];
    }

    protected final void enlarge(final int pages) {
        final long[][] new_block = new long[pages][];
        final int oldlen = _block.length;
        System.arraycopy(_block, 0, new_block, 0, oldlen);
        new_block[oldlen] = new long[PAGE_LENGTH];
        this._block = new_block;
    }

    protected void setData(final long at, final long value) {
        if(value > 0x7fffffffL) {
            throw new IllegalArgumentException("Illegal value: " + value);
        }
        final long lp = at >> PAGE_SHIFT;
        if(lp > 0x7fffffffL) {
            throw new IllegalStateException("Illegal page number: " + lp);
        }
        final int page = (int) lp;
        if(page >= _block.length) {
            enlarge((int) (_block.length * ENLARGE_PAGES_FACTOR));
        }
        long[] block = _block[page];
        if(block == null) {
            block = new long[PAGE_LENGTH];
            this._block[page] = block;
        }
        final int i = (int) (at & PAGE_MASK);
        block[i] = value;
    }

    @Override
    protected void fastSetData(final long at, final long value) {
        if(value > 0x7fffffffL) {
            throw new IllegalArgumentException("Illegal value: " + value);
        }
        this._block[(int) (at >> PAGE_SHIFT)][(int) (at & PAGE_MASK)] = value;
    }

    protected void setFlag(final long at, final int flag) {
        final long lp = at >> PAGE_SHIFT;
        if(lp > 0x7fffffffL) {
            throw new IllegalStateException("Illegal page number: " + lp);
        }
        final int page = (int) lp;
        if(page >= _block.length) {
            enlarge((int) (_block.length * ENLARGE_PAGES_FACTOR));
        }
        long[] block = _block[page];
        if(block == null) {
            block = new long[PAGE_LENGTH];
            this._block[page] = block;
        }
        block[(int) (at & PAGE_MASK)] |= flag;
    }

    @Override
    protected void fastSetFlag(final long at, final int flag) {
        this._block[(int) (at >> PAGE_SHIFT)][(int) (at & PAGE_MASK)] |= flag;
    }

    public void flush(final DbCollection coll, final String docName) throws IOException,
            DbException {
        PropertyMap properties = coll.getCollectionProperties();

        // #1 sync local symbol table with shared symbol table
        if(BUILDIDX_AT_BULKLOAD) {
            flushAuxiliaries(coll, docName, properties);
        }
        coll.flushSymbols();

        // #2 write properties
        writeProperties(docName, properties);

        // #3 write str chunk
        _strChunk.flush(coll, docName, properties);

        properties.save();

        // #4 write block in segment paged files
        FixedSegments paged = getPaged(coll, docName);
        pageOutSegments(paged, 0, true);
        close();
    }

    private final void writeProperties(final String docName, final PropertyMap properties)
            throws IOException {
        properties.setProperty(KEY_DTM_CLASS + docName, DTM_CLASS);
        properties.setProperty(KEY_BLOCK_PTR + docName, String.valueOf(_blockPtr));
        int usedPages = (int) _blockPtr / PAGE_LENGTH;
        if(_blockPtr % PAGE_LENGTH != 0) {
            usedPages += 1;
        }
        properties.setProperty(KEY_USED_PAGES + docName, String.valueOf(usedPages));
    }

    private final void flushAuxiliaries(final DbCollection coll, final String docName, final PropertyMap docProps)
            throws DbException {
        final DocumentTableModel dtm = new DocumentTableModel(this);
        final LabelingHandler handler = new LabelingHandler(this, coll, docName, docProps);
        try {
            dtm.export(0, handler);
        } catch (XQueryException e) {
            throw new IllegalStateException(e);
        }
        final QNameTable sharedQNames = coll.getSymbols().getQnameTable();
        sharedQNames.load(_nameTable);
        sharedQNames.setDirty(true);
    }

    public FixedSegments getPaged(final DbCollection coll, final String docName) {
        final File baseDir = new File(coll.getAbsolutePath());
        final File segFile = new File(baseDir, docName + DTM_SEGMENT_FILE_SUFFIX);
        return new FixedSegments(segFile, PAGE_SIZE);
    }

    private final int pageOutSegments(final FixedSegments paged, final int beginPage, boolean toend)
            throws IOException {
        final long[][] blockRef = _block;
        final FastMultiByteArrayOutputStream buf = new FastMultiByteArrayOutputStream(DEFAULT_EXTENT_SIZE);
        final long lp = (_blockPtr >> PAGE_SHIFT) + 1;
        if(lp > 0x7fffffffL) {
            throw new IllegalStateException("Illegal page number: " + lp);
        }
        final int usedPages = (int) lp;
        int cnt = 0;
        for(int i = beginPage; i <= usedPages; i += DEFAULT_EXTENT_THRESHOLD) {
            final int pages;
            if((i + DEFAULT_EXTENT_THRESHOLD) > usedPages) {
                if(!toend) {
                    break;
                }
                pages = usedPages - i;
            } else {
                pages = DEFAULT_EXTENT_THRESHOLD;
            }
            int addr = i;
            final int limit = Math.min(i + pages, blockRef.length);
            for(int p = i; p < limit; p++) {
                final long[] ref = blockRef[p];
                if(ref == null) {
                    if(buf.size() > 0) {
                        final byte[] b = buf.toByteArray();
                        buf.reset();
                        paged.bulkWrite(addr, b);
                    }
                } else {
                    if(buf.size() == 0) {
                        addr = p;
                    }
                    buf.writeLongs(ref, 0, ref.length);
                    blockRef[p] = null;
                    ++cnt;
                }
            }
            if(buf.size() > 0) {
                final byte[] b = buf.toByteArray();
                buf.reset();
                paged.bulkWrite(addr, b);
            }
        }
        buf.close();
        paged.flush(false);
        if(LOG.isInfoEnabled()) {
            LOG.info("paged out docuemnt table seqment(" + beginPage + " - " + (beginPage + cnt)
                    + ") to disk: " + paged.getFile().getAbsolutePath());
        }
        return cnt;
    }

    static final IDocumentTable load(final DbCollection coll, final String docName, final PropertyMap docProps)
            throws IOException {
        return new PersistentBigDocumentTable(coll, docName, docProps);
    }

    public static final class PersistentBigDocumentTable extends BigDocumentTable {
        private static final long serialVersionUID = 5187802360153356037L;

        private static final int pageOutThreshold = 32;

        private final FixedSegments _paged;
        /** 4K * 16 * 1024 = 64MB */
        private final IntHash<long[]> _pageReadCache;
        private final ObservableLongLRUMap<long[]> _dirtyBuffer;
        private final PagingProfile _profile = new PagingProfile();

        private int lastPagedOutSegment = 0;
        private int prevBlocks = -1;
        private byte[] tmpBlock = null;

        private final ILock _lock = new AtomicBackoffLock();

        protected PersistentBigDocumentTable(final DbCollection coll, final String docName, final PropertyMap docProps) {
            super(coll, docName, docProps);
            final FixedSegments paged = loadSegments(coll, docName);
            this._paged = paged;
            final IntHash<long[]> readCache = ENV_USE_2QCACHE ? new Int2QCache<long[]>(PAGE_CACHE_SIZE)
                    : new IntLRUMap<long[]>(PAGE_CACHE_SIZE);
            this._pageReadCache = readCache;
            this._dirtyBuffer = new ObservableLongLRUMap<long[]>(256, 32, new BufferWriter(paged, readCache));
        }

        public PersistentBigDocumentTable(final DbCollection coll, final String docName) {
            super(coll, docName);
            final FixedSegments paged = loadSegments(coll, docName);
            this._paged = paged;
            final IntLRUMap<long[]> readCache = new IntLRUMap<long[]>(PAGE_CACHE_SIZE);
            this._pageReadCache = readCache;
            this._dirtyBuffer = new ObservableLongLRUMap<long[]>(256, 32, new BufferWriter(paged, readCache));
        }

        @Override
        public void close() throws IOException {
            if(_refcount.decrementAndGet() == -1) {
                _dirtyBuffer.clear();
                PrivilegedAccessor.unsafeSetField(this, PersistentBigDocumentTable.class, "_pageReadCache", null);
                _close();
                _paged.close();
            }
        }

        private static FixedSegments loadSegments(final DbCollection coll, final String docName) {
            final File segFile = new File(coll.getDirectory(), docName + DTM_SEGMENT_FILE_SUFFIX);
            return new FixedSegments(segFile, PAGE_SIZE);
        }

        @Override
        public FixedSegments getPaged(final DbCollection coll, final String docName) {
            return _paged;
        }

        @Override
        public PagingProfile getPagingProfile() {
            return _profile;
        }

        @Override
        public void closeNode() {
            final long lcp = _curNode >>> PAGE_SHIFT;
            final int curpage = (int) lcp;
            final int curSeg = (curpage / DEFAULT_EXTENT_THRESHOLD) - 1;
            final int diff = curSeg - lastPagedOutSegment;
            if(diff > pageOutThreshold) {
                final int beginPage = lastPagedOutSegment * DEFAULT_EXTENT_THRESHOLD;
                final int endPage = curSeg * DEFAULT_EXTENT_THRESHOLD - 1;
                try {
                    pageOutSegments(_paged, beginPage, endPage);
                } catch (IOException e) {
                    throw new IllegalStateException("paged out failed", e);
                }
                this.lastPagedOutSegment = curSeg;
            }
            super.closeNode(); // move up to parent
        }

        private void pageOutSegments(final FixedSegments paged, final int beginPage, final int endPage)
                throws IOException {
            final long[][] blockRef = _block;
            final FastMultiByteArrayOutputStream buf = new FastMultiByteArrayOutputStream(DEFAULT_EXTENT_SIZE);
            for(int i = beginPage; i <= endPage; i += DEFAULT_EXTENT_THRESHOLD) {
                final int pages;
                if((i + DEFAULT_EXTENT_THRESHOLD - 1) > endPage) {
                    break;
                } else {
                    pages = DEFAULT_EXTENT_THRESHOLD;
                }
                int addr = i;
                final int limit = Math.min(i + pages, blockRef.length - 1);
                for(int p = i; p < limit; p++) {
                    final long[] ref = blockRef[p];
                    if(ref == null) {
                        if(buf.size() > 0) {
                            final byte[] b = buf.toByteArray();
                            buf.reset();
                            paged.bulkWrite(addr, b);
                        }
                    } else {
                        if(buf.size() == 0) {
                            addr = p;
                        }
                        buf.writeLongs(ref, 0, ref.length);
                        blockRef[p] = null;
                    }
                }
                if(buf.size() > 0) {
                    final byte[] b = buf.toByteArray();
                    buf.reset();
                    paged.bulkWrite(i, b);
                }
            }
            buf.close();
            paged.flush(false);
            if(LOG.isInfoEnabled()) {
                LOG.info("paged out docuemnt table seqment(" + beginPage + " - " + endPage
                        + ") to disk: " + paged.getFile().getAbsolutePath());
            }
        }

        @Override
        protected void setFlag(final long at, final int flag) {
            final long value = this.dataAt(at) | flag;
            this.setData(at, value);
        }

        @Override
        protected void fastSetFlag(final long at, final int flag) {
            this.setFlag(at, flag);
        }

        @Override
        public long dataAt(final long at) {
            if(at > _blockPtr) {// not initialized                
                return 0;
            }
            final long lp = at >> PAGE_SHIFT;
            assert (lp <= 0x7fffffffL) : "Last page number is invalid: " + lp;
            final int requiredPage = (int) lp;
            final long[][] blocksRef = _block;
            if(requiredPage >= blocksRef.length) {//prevent index of out error
                return 0;
            }
            final long[] page = blocksRef[requiredPage];
            if(page == null) {// page in DTM segments
                if(at == _blockPtr) {
                    return 0;
                }
                final long[] readBlock = pageInSegments(_paged, requiredPage);
                final int bi = (int) (at & PAGE_MASK);
                return readBlock[bi];
            }
            final int bi = (int) (at & PAGE_MASK);
            return page[bi];
        }

        @Override
        protected void setData(final long at, final long value) {
            final long lp = at >> PAGE_SHIFT;
            if(lp > 0x7fffffffL) {
                throw new IllegalStateException("Last page number is invalid: " + lp);
            }
            final int requiredPage = (int) lp;
            final int blocklen = _block.length;
            if((requiredPage < blocklen) && (_block[requiredPage] == null)) {
                final int segnum = requiredPage / DEFAULT_EXTENT_THRESHOLD;
                if(segnum < lastPagedOutSegment) {
                    final long[] readBlock = pageIn(_paged, requiredPage);
                    final int bi = (int) (at & PAGE_MASK);
                    readBlock[bi] = value;
                    _dirtyBuffer.put(requiredPage, readBlock);
                    return;
                }
            }
            if(requiredPage >= blocklen) {
                enlarge((int) (blocklen * ENLARGE_PAGES_FACTOR));
            }
            long[] block = _block[requiredPage];
            if(block == null) {
                block = new long[PAGE_LENGTH];
                _block[requiredPage] = block;
            }
            final int i = (int) (at & PAGE_MASK);
            block[i] = value;
        }

        @Override
        protected void fastSetData(final long at, final long value) {
            this.setData(at, value);
        }

        // REVIEWME synchronized
        private final long[] pageIn(final FixedSegments paged, final int requiredPage) {
            final IntHash<long[]> cache = _pageReadCache;
            long[] readBlock = cache.get(requiredPage);
            if(readBlock != null) {
                return readBlock;
            }
            final byte[] v;
            try {
                v = paged.read(requiredPage);
            } catch (IOException e) {// should not come here
                throw new IllegalStateException("segment #" + requiredPage + " was not found.");
            }
            readBlock = Primitives.toLongs(v);
            cache.put(requiredPage, readBlock);
            return readBlock;
        }

        private final long[] pageInSegments(final FixedSegments paged, final int requiredPage) {
            long[] readBlock = getBlock(requiredPage);
            if(readBlock != null) {
                return readBlock;
            }
            final long[][] blocksRef = _block;
            final PagingProfile profile = _profile;
            final int pageIdx = profile.getReadBackwards();
            int fromPage = Math.max(requiredPage - pageIdx, 0);
            int toPage = Math.min(requiredPage + profile.getReadForwards() + 1, blocksRef.length);
            for(int i = fromPage; i < requiredPage; i++) {
                if(blocksRef[i] == null) {
                    break;
                } else {
                    fromPage++;
                }
            }
            for(int i = toPage - 1; i > requiredPage; i--) {
                if(blocksRef[i] == null) {
                    break;
                } else {
                    toPage--;
                }
            }
            final int numBlocks = toPage - fromPage;
            profile.lastReadAccess(requiredPage, numBlocks);
            if(LOG.isDebugEnabled()) {
                LOG.debug("page in " + numBlocks + " blocks [" + fromPage + ',' + toPage + ')');
            }
            final byte[] v;
            if(numBlocks == prevBlocks) {
                v = tmpBlock;
                try {
                    paged.bulkRead(fromPage, v);
                } catch (IOException e) {
                    throw new IllegalStateException("failed reading " + numBlocks
                            + " segments from #" + fromPage);
                }
            } else {
                try {
                    v = paged.bulkRead(fromPage, numBlocks);
                } catch (IOException e) {
                    throw new IllegalStateException("failed reading " + numBlocks
                            + " segments from #" + fromPage);
                }
                this.tmpBlock = v;
                this.prevBlocks = numBlocks;
            }
            for(int i = 0, off = 0; i < numBlocks; i++, off += PAGE_SIZE) {
                final long[] b = Primitives.toLongs(v, off, PAGE_SIZE);
                final int pi = fromPage + i;
                if(pi == requiredPage) {
                    readBlock = b;
                    putBlock(requiredPage, b);
                } else {
                    putBlock(pi, b);
                }
            }
            if(readBlock == null) {
                throw new IllegalStateException("Page not found: " + requiredPage);
            }
            return readBlock;
        }

        private long[] getBlock(final int page) {
            final ILock lock = _lock;
            lock.lock();
            try {
                return _pageReadCache.get(page);
            } finally {
                lock.unlock();
            }
        }

        private void putBlock(final int page, final long[] b) {
            final ILock lock = _lock;
            lock.lock();
            try {
                _pageReadCache.putIfAbsent(page, b);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void flush(final DbCollection coll, final String docName) throws IOException,
                DbException {
            _dirtyBuffer.purgeAll();
            super.flush(coll, docName);
            _dirtyBuffer.purgeAll();
        }

    }

    private static final class BufferWriter implements Cleaner<long[]> {

        private final FixedSegments paged;
        private final IntHash<long[]> readCache;

        BufferWriter(final FixedSegments paged, final IntHash<long[]> readCache) {
            super();
            this.paged = paged;
            this.readCache = readCache;
        }

        public void cleanup(final long idx, final long[] value) {
            final byte[] b = Primitives.toBytes(value);
            try {
                paged.write(idx, b);
            } catch (IOException e) {
                throw new IllegalStateException("failed to write #" + idx, e);
            }
            readCache.put((int) idx, value);
        }

    }
}