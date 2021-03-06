/*
 * @(#)$Id: LRUCacheProvider.java 3619 2008-03-26 07:23:03Z yui $
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
package xbird.util.cache.provider;

import java.util.Properties;

import xbird.config.Settings;
import xbird.util.cache.*;
import xbird.util.cache.algorithm.LRUCache;

/**
 * Factory of LRUCache.
 * <DIV lang="en"></DIV>
 * <DIV lang="ja"></DIV>
 * 
 * @author Makoto YUI <yuin405+xbird@gmail.com>
 */
public class LRUCacheProvider implements CacheProvider {

    private static final String DEFAULT_MAX_CACHE_ENTRIES = Settings.get(CacheFactory.PROP_MAX_CACHE_ENTRIES);

    public LRUCacheProvider() {}

    public Cache buildCache(String regionName, Properties properties) {
        String v = properties.getProperty(CacheFactory.PROP_MAX_CACHE_ENTRIES);
        String maxEntries = (v == null) ? DEFAULT_MAX_CACHE_ENTRIES : v;
        if (maxEntries != null) {
            int m = new Integer(maxEntries).intValue();
            return new LRUCache(regionName, m);
        } else {
            return new LRUCache(regionName);
        }
    }

}