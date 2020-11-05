package com.leetcode.algorithm.cacheelimination;

import java.util.Scanner;

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
 *  https://github.com/kris-2018/algo/blob/master/java/06_linkedlist/LRUBaseLinkedList.java
 *
 * 思路:
 * 维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
 *  1. 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
 *  2. 如果此数据没有在缓存链表中，又可以分为两种情况：
 *      如果此时缓存未满，则将此结点直接插入到链表的头部；
 *      如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
 *
 */
public class LRUBaseLinkedList<T> {

    public static void main(String[] args) {
        LRUBaseLinkedList list = new LRUBaseLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.printAll();
        list.add(5);
        list.printAll();
        /*Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }*/
    }


    private final static Integer DEFAULT_CAPACITY = 3; //链表容量
    private SNode<T> headNode; //头结点
    private Integer length; //长度
    private Integer capacity; //容量

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = capacity;
    }
    public void add(T data) {
        SNode preNode = findPreNode(data);
        // 链表中存在，删除原数据，再插入到链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            intsertElemAtBegin(data);
        } else {
            if (length > capacity) {
                //删除尾结点
                deleteElemAtEnd();
            }
            intsertElemAtBegin(data);
        }
    }
    /* 删除尾结点 */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        // 空链表直接返回
        if (ptr.getNext() == null) {
            return;
        }
        // 倒数第二个结点
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }
    /* 链表头部插入节点 */
    private void intsertElemAtBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        length++;
    }
    /* 删除preNode结点下一个元素 */
    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /* 获取查找到元素的前一个结点 */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    private void printAll() {
        SNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

}

class SNode<T> {
    private T element;
    private SNode next;

    public SNode(T element) {
        this.element = element;
    }
    public SNode(T element, SNode next) {
        this.element = element;
        this.next = next;
    }

    public SNode() {
        this.next = null;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public SNode getNext() {
        return next;
    }

    public void setNext(SNode next) {
        this.next = next;
    }
}
