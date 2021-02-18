package com.evael.community.common.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 线程变量
 */
public class ThreadLocalContext {

    public static String CORRELATION_ID ="correlationId";

    private static final ThreadLocal<Map<String, Object>> context = new ThreadLocal<Map<String, Object>>() {
        protected synchronized Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    public static Map<String, Object> getAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        for (String key : context.get().keySet()) {
            result.put(key, context.get().get(key));
        }
        return result;
    }

    public static Object get(String key)
    {
       return context.get().get(key);
    }


    public static void put(String key, Object value) {
        context.get().put(key, value);
    }


    public static void clear() {
        //contextMap.clear();
        context.set(new HashMap<String, Object>());
    }


}
