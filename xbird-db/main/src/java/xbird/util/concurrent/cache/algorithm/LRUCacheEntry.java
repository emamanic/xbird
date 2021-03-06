/*
 * @(#)$Id$
 *
 * Copyright 2008 Makoto YUI
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

import xbird.util.cache.CacheEntry;

/**
 * 
 * <DIV lang="en"></DIV>
 * <DIV lang="ja"></DIV>
 * 
 * @author Makoto YUI (yuin405+xbird@gmail.com)
 */
public final class LRUCacheEntry<K, V> extends CacheEntry<K, V> {
    private static final long serialVersionUID = 168818517409134191L;

    protected LRUCacheEntry next, prev;

    public LRUCacheEntry(K key, V value) {
        super(key, value);
    }

    public void recordAccess(LRUCacheEntry<K, V> headerEntry) {
        remove();
        addBefore(headerEntry);
    }

    public void recordRemoval() {
        remove();
    }

    private void remove() {
        if(prev != null) {
            prev.next = next;
        }
        if(next != null) {
            next.prev = prev;
        }
        prev = null;
        next = null;
    }

    protected void addBefore(LRUCacheEntry<K, V> existingEntry) {
        next = existingEntry;
        prev = existingEntry.prev;
        if(prev != null) {
            prev.next = this;
        }
        next.prev = this;
    }

}
