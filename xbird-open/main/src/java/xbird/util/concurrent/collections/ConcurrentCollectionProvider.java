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
package xbird.util.concurrent.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.cliffc.high_scale_lib.NonBlockingHashMap;
import org.cliffc.high_scale_lib.NonBlockingHashMapLong;

import xbird.util.system.SystemUtils;

/**
 * 
 * <DIV lang="en"></DIV>
 * <DIV lang="ja"></DIV>
 * 
 * @author Makoto YUI (yuin405+xbird@gmail.com)
 */
public final class ConcurrentCollectionProvider {

    private static final boolean FORCE_CLIFFC_HASH = "cliffc".equals(System.getProperty("xbird.concurrent.map"));

    private ConcurrentCollectionProvider() {}

    public static <K, V> ConcurrentMap<K, V> createConcurrentMap(int size) {
        final int procs = SystemUtils.availableProcessors();
        if(FORCE_CLIFFC_HASH || procs > 8) {
            return new NonBlockingHashMap<K, V>(size);
        }
        return new ConcurrentHashMap<K, V>(size);
    }

    public static <V> ConcurrentMap<Long, V> createConcurrentMapLong(int size) {
        final int procs = SystemUtils.availableProcessors();
        if(FORCE_CLIFFC_HASH || procs > 8) {
            return new NonBlockingHashMapLong<V>(size);
        }
        return new ConcurrentHashMapLong<V>(size);
    }

    public static <V> ConcurrentMap<?, V> createConcurrentMap(int size, Class keyClass) {
        if(keyClass == Long.class) {
            return createConcurrentMapLong(size);
        }
        return createConcurrentMap(size);
    }
}
