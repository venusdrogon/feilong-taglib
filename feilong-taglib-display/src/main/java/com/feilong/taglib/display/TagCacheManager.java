/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.taglib.display;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.Validator;

/**
 * The Class PagerCacheManager.
 *
 * @author feilong
 * @version 1.5.4 2016年4月19日 上午2:26:10
 * @since 1.5.4
 * @see "com.google.common.cache.Cache"
 */
//XXX 将来可能会有更好的做法
public final class TagCacheManager{

    /** The Constant LOGGER. */
    private static final Logger                  LOGGER       = LoggerFactory.getLogger(TagCacheManager.class);

    /**
     * 设置缓存是否开启.
     */
    private static final boolean                 CACHE_ENABLE = true;

    /**
     * 将结果缓存到map.
     * <p>
     * key是入参对象,value是解析完的字符串<br>
     * 该cache里面value不会存放null/empty
     * </p>
     */
    private static final Map<CacheParam, String> CACHE        = new ConcurrentHashMap<CacheParam, String>();

    /** Don't let anyone instantiate this class. */
    private TagCacheManager(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 从缓存中读取.
     *
     * @param cacheParam
     *            the pager params
     * @return the content from cache
     */
    public static String getContentFromCache(CacheParam cacheParam){
        //缓存
        if (CACHE_ENABLE){
            LOGGER.debug("cache.size:{}", CACHE.size());
            if (CACHE.containsKey(cacheParam)){
                LOGGER.info("hashcode:[{}],get info from cache", cacheParam.hashCode());
                return CACHE.get(cacheParam);
            }
            LOGGER.info("hashcode:[{}],cache not contains [{}],will do parse", cacheParam.hashCode(), cacheParam.getClass().getName());
        }else{
            LOGGER.info("the cache status is disable!");
        }
        return StringUtils.EMPTY;
    }

    /**
     * 设置.
     *
     * @param cacheParam
     *            the pager params
     * @param content
     *            the content
     */
    public static void put(CacheParam cacheParam,String content){
        if (CACHE_ENABLE && Validator.isNotNullOrEmpty(content)){//设置cache
            CACHE.put(cacheParam, content);
        }
    }
}