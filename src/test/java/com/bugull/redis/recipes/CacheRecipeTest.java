/*
 * Copyright (c) www.bugull.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bugull.redis.recipes;

import com.bugull.redis.RedisConnection;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Frank Wen(xbwen@hotmail.com)
 */
public class CacheRecipeTest {
    
    @Test
    public void test() throws Exception {
        RedisConnection conn = RedisConnection.getInstance();
        conn.setHost("127.0.0.1");
        conn.setPassword("foobared");
        conn.connect();
        
        String key = "key";
        String value = "value";
        
        CacheRecipe cache = new CacheRecipe();
        cache.put(key, 10, value);  //10 seconds
        
        Thread.sleep(6 * 1000);  //6 seconds
        Assert.assertEquals(value, cache.get(key));
        
        Thread.sleep(6 * 1000);  //6 seconds
        Assert.assertNull(cache.get(key));
        
        conn.disconnect();
    }

}
