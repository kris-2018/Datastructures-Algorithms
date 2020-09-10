package com.leetcode.algorithm.cacheelimination;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *
 * 示例:
 * LRUCache cache = new LRUCache( 2 )
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * https://leetcode.com/problems/lru-cache/
 *
 * */


/*
* 使用 LinkedHashMap
* HashMap底层是 数组 + 红黑树 + 链表, 是无序的。
* LinkedHashMap<K,V> extends HashMap<K,V>, 比HashMap多一个有序(按插入顺序 or 按读取顺序)的功能;
* 其内部是靠建立一个双向链表来维护这个顺序:
* 每次插入、删除后，都会调用一个函数来进行 双向链表的维护( afterNodeAccess| afterNodeRemoval| afterNodeInsertion )
* */
public class LeetCode146LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;
    public LeetCode146LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    // 这个可不写
    public void put(int key, int value) {
        super.put(key, value);
    }

    /* 重写 removeEldestEntry()函数，就能拥有我们自己的缓存策略 */
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
    public static void main(String[] args) {
        LeetCode146LRUCache lruCache = new LeetCode146LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));

        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));

    }
}

/*
    private final Map<Integer, Integer> map;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }*/
