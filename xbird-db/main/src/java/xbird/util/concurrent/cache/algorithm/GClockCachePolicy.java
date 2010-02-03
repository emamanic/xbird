/*
 * @(#)$Id: codetemplate_xbird.xml 943 2006-09-13 07:03:37Z yui $
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
package xbird.util.concurrent.cache.algorithm;

import java.util.concurrent.ConcurrentMap;

import xbird.util.cache.Cleaner;
import xbird.util.cache.ICacheEntry;
import xbird.util.concurrent.cache.ReplacementPolicy;
import xbird.util.concurrent.cache.helpers.ClockBuffer;
import xbird.util.concurrent.cache.helpers.NopCleaner;

/**
 * In GCLOCK, a counter is associated with each page, whose initial value is assigned 
 * when the page is brought into the buffer.
 * <DIV lang="en">
 * Victor F. Nicola, Asit Dan, Daniel M. Dias: 
 * Analysis of the Generalized Clock Buffer Replacement Scheme for Database Transaction Processing.
 * SIGMETRICS pp. 35-46, 1992.
 * </DIV>
 * <DIV lang="ja"></DIV>
 * 
 * @author Makoto YUI (yuin405+xbird@gmail.com)
 */
public class GClockCachePolicy<K, V> implements ReplacementPolicy<K, V, GClockCacheEntry<K, V>> {

    private final int _maxCapacity;
    private final ClockBuffer<K, V> _buf;

    private Cleaner<K, V> _cleaner = NopCleaner.getInstance();

    public GClockCachePolicy(int capacity) {
        this._maxCapacity = capacity;
        this._buf = new ClockBuffer<K, V>(capacity);
    }

    public final void setCleaner(Cleaner<K, V> cleaner) {
        this._cleaner = cleaner;
    }

    public final int getMaxCapacity() {
        return _maxCapacity;
    }

    public final void recordAccess(final GClockCacheEntry<K, V> entry) {
        entry.incrReferenceCount();
    }

    public final void recordRemoval(final GClockCacheEntry<K, V> entry) {
        entry.setReferenceCount(Integer.MIN_VALUE);
    }

    public final V addEntry(final ConcurrentMap<K, GClockCacheEntry<K, V>> map, final K key, final V value, final boolean replace) {
        final GClockCacheEntry<K, V> newEntry = createCacheEntry(key, value, false);
        final GClockCacheEntry<K, V> removed = _buf.add(newEntry);
        if(removed != null) {
            K remotedKey = removed.getKey();
            _cleaner.cleanup(remotedKey, removed.getValue());
            map.remove(remotedKey, removed);
        }
        map.put(key, newEntry);
        return null;
    }

    public final ICacheEntry<K, V> allocateEntry(final ConcurrentMap<K, GClockCacheEntry<K, V>> map, final K key, final V value) {
        final GClockCacheEntry<K, V> newEntry = createCacheEntry(key, value, true);
        final GClockCacheEntry<K, V> removed = _buf.add(newEntry);
        if(removed != null) {
            K remotedKey = removed.getKey();
            map.remove(remotedKey);
            _cleaner.cleanup(remotedKey, removed.getValue());
        }
        final GClockCacheEntry<K, V> prevEntry = map.putIfAbsent(key, newEntry);
        if(prevEntry == null) {
            return newEntry;
        } else {
            if(prevEntry.pin()) {
                newEntry.setEvicted();
                prevEntry.incrReferenceCount();
                return prevEntry;
            } else {
                map.put(key, newEntry);
                newEntry.setValue(prevEntry.getValue());
                return newEntry;
            }
        }
    }

    protected GClockCacheEntry<K, V> createCacheEntry(final K key, final V value, final boolean pin) {
        return new GClockCacheEntry<K, V>(key, value, pin);
    }

    public int prefetchEntries(ICacheEntry<K, V> entry) {
        return 0;
    }

}
