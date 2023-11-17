package com.hex.wetech.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class CacheMapUtil {
    private static final Map<String, Map<Object, Object>> CACHE_MAP = new ConcurrentHashMap<>();
    private static final Map<String, Object> CACHE_OBJECT = new ConcurrentHashMap<>();

    public static <K, V> ConcurrentHashMap<K, V> newCacheMap(String key) {
        CACHE_MAP.put(key, new ConcurrentHashMap<>());
        return (ConcurrentHashMap<K, V>) CACHE_MAP.get(key);
    }
    public static <K, V> ConcurrentHashMap<K, V> newCacheMapIfAbsent(String key) {
        if (CACHE_MAP.containsKey(key)) {
            return (ConcurrentHashMap<K, V>) CACHE_MAP.get(key);
        }
        CACHE_MAP.put(key, new ConcurrentHashMap<>());
        return (ConcurrentHashMap<K, V>) CACHE_MAP.get(key);
    }

    public static <K, V> ConcurrentHashMap<K, V> newCacheObjectMap(String key) {
        CACHE_OBJECT.put(key, new ConcurrentHashMap<>());
        return (ConcurrentHashMap<K, V>) CACHE_OBJECT.get(key);
    }

    public static Object get(String key, Object key2) {
        return CACHE_MAP.getOrDefault(key, new ConcurrentHashMap<>()).get(key2);
    }

    public static <T> T get(String key, Object key2, Class<T> t) {
        return (T) get(key, key2);
    }

    public static Object getCacheObject(String key) {
        return CACHE_OBJECT.get(key);
    }

    public static <T> T getCacheObject(String key, Class<T> t) {
        return (T) CACHE_OBJECT.get(key);
    }

    public static void set(String key, Object key2, Object value) {
        CACHE_MAP.get(key).put(key2, value);
    }

    public static void setCacheObject(String key, Object value) {
        CACHE_OBJECT.put(key, value);
    }

    public static Map<Object, Object> getCacheMapByKey(String key) {
        return CACHE_MAP.computeIfAbsent(key, (k) -> newCacheMap(key));
//        return CollectionUtil.computeIfAbsent(CACHE_MAP, key, (k) -> newCacheMap(key));
    }

    public static Map<String, Map<Object, Object>> getCacheMap() {
        return CACHE_MAP;
    }

    public static Map<String, Object> getCacheObjectMap(String key) {
        return CACHE_OBJECT;
    }
}
