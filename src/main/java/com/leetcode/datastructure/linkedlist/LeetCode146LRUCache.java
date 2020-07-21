package com.leetcode.datastructure.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

public class LeetCode146LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;
    public LeetCode146LRUCache(int initialCapacity) {
        super(initialCapacity,0.75F, true);
        this.capacity = capacity;
    }
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
    public static void main(String[] args) {
        final LeetCode146LRUCache lruCache = new LeetCode146LRUCache(3);
        lruCache.put(1, 101);
        lruCache.put(2, 102);
        lruCache.put(3, 103);
        lruCache.put(4, 104);
        System.out.println(lruCache.get(2));

    }
}
