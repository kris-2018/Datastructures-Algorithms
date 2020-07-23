package com.leetcode.datastructure.linkedlist;

/**
 * LRU 缓存
 * 缓存是一种提高数据读取性能的技术，在硬件设计、软件开发中都有着非常广泛的应用，比如常见的 CPU 缓存、数据库缓存、浏览器缓存等等。
 * <p>
 * 常见的缓存淘汰策略
 * 先进先出策略 FIFO（First In，First Out）
 * 最少使用策略 LFU（Least Frequently Used）
 * 最近最少使用策略 LRU（Least Recently Used）
 * 假如你买了很多本技术书，但有一天你发现，这些书太多了，太占书房空间了，你要做个大扫除，扔掉一些书籍。那这个时候，你会选择扔掉哪些书呢？
 * <p>
 * LRU缓存的实现
 * 思路：维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，从链表头开始顺序遍历链表：
 * 如果此数据之前已经被缓存在链表中了，遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
 * 如果此数据没有在缓存链表中,，则将此结点插入到链表的头部；
 * 如果此时缓存超过容量，则链表尾结点删除。
 */
public class LRUBaseLinkedList {


}
