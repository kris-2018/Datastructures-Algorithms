package com.leetcode.datastructure.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用 LinkedHashMap
 * HashMap底层是 数组 + 红黑树 + 链表, 是无序的。
 * LinkedHashMap<K,V> extends HashMap<K,V>, 比HashMap多一个有序(按插入顺序 or 按读取顺序)的功能;
 * 其内部是靠建立一个双向链表来维护这个顺序:
 *   每次插入、删除后，都会调用一个函数来进行 双向链表的维护( afterNodeAccess| afterNodeRemoval| afterNodeInsertion )
 */
public class LRUCache {
    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
    }
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key);
    }
    public void put(int key, int value) {
        map.put(key, value);
    }

    private static class LinkedCappedHashMap<K, V> extends LinkedHashMap<K, V> {
        int maximumCapacity;

        LinkedCappedHashMap(int maximumCapacity) {
            // initialCapacity代表map的容量, loadFactor代表加载因子, accessOrder默认false,如果要按读取顺序排序需要将其设为true
            super(16, 0.75f, true);//default initial capacity (16) and load factor (0.75) and accessOrder (false)
            this.maximumCapacity = maximumCapacity;
        }
        /* 重写 removeEldestEntry()函数，就能拥有我们自己的缓存策略 */
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > maximumCapacity;
        }
    }

    public static void main(String[] args) {
        final LRUCache lruCache = new LRUCache(10);
        lruCache.put(1, 1001);
        lruCache.put(2, 1002);
        lruCache.put(3, 1003);
        System.out.println(lruCache.get(1));
    }
}
