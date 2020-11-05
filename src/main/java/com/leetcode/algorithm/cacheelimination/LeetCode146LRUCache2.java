package com.leetcode.algorithm.cacheelimination;

import java.util.Hashtable;

/**
 * 哈希表 + 双向链表
 * 时间复杂度：对于 put 和 get 都是 O(1)
 * 空间复杂度：O(capacity),因为哈希表和双向链表最多存储 capacity+1 个元素。
 * 算法
 * LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
 *      双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
 *      哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
 * 这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 O(1)的时间内完成 get 或者 put 操作。具体的方法如下：
 * 对于 get 操作，首先判断 key 是否存在：
 *      如果 key 不存在，则返回 −1；
 *      如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
 * 对于 put 操作，首先判断 key 是否存在：
 *      如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
 *      如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。
 *      上述各项操作中，访问哈希表的时间复杂度为 O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为 O(1)。而将一个节点移到双向链表的头部，可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作，都可以在 O(1)时间内完成。
 *
 * 小贴士
 * 在双向链表的实现中，使用一个伪头部（dummy head）和伪尾部（dummy tail）标记界限，这样在添加节点和删除节点的时候就不需要检查相邻的节点是否存在。
 *
 */
public class LeetCode146LRUCache2 {

    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;
    public LeetCode146LRUCache2(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        // dummy head <--> dummy tail
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在(说明key 对应的节点是最近被使用), 先通过哈希表定位到该节点在双向链表中的位置, 再移到双向链表的头部
        moveToHead(node); // move the accessed node to the head;
        return node.value;
    }
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key); //获取当前key是否有值
        if (node == null) { // 如果 key 不存在
            DLinkedNode newNode = new DLinkedNode(key, value); // 创建一个新的节点
            cache.put(key, newNode);  // 添加进哈希表
            addToHead(newNode); // 添加至双向链表的头部
            count++;
            if (count > capacity) { // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                cache.remove(tail.key); // 删除哈希表中对应的项
                count--;
            }
        } else { // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            this.moveToHead(node);
        }

    }


    class DLinkedNode {
        int key;
        int value;
        DLinkedNode next;
        DLinkedNode prev;

        public DLinkedNode() {}
        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    /* 添加第一个节点时放在tail之前, 第二个节点放第一个节点之前, 以此类推 */
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    /* 删除当前的k, v */
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }
    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    public static void main(String[] args) {
        LeetCode146LRUCache2 cache = new LeetCode146LRUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(2, 22);
        //cache.put(3, 3);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println("阈值满了之后, 删除链表尾部的元素");
        cache.put(3, 3);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }
}
