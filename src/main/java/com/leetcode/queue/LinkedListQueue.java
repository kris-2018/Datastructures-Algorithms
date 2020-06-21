package com.leetcode.queue;

import com.leetcode.linkedlist.ListNode;

/**
 * 基于链表实现的队列
 */
public class LinkedListQueue {
    public static void main(String[] args) {
        LinkedListQueue linkedListQueue = new LinkedListQueue();
        linkedListQueue.enqueue(1);
        linkedListQueue.enqueue(2);
        linkedListQueue.enqueue(3);
        linkedListQueue.printAll();
        linkedListQueue.dequeue();
        linkedListQueue.printAll();
    }

    private ListNode head = null;
    private ListNode tail = null;

    //入队
    public void enqueue(int value) {
        if (tail == null) {
            ListNode new_Node = new ListNode(value, null);
            head = new_Node;
            tail = new_Node;
        } else {
            tail.next = new ListNode(value, null);
            tail = tail.next;
        }
    }
    //出队
    public Integer dequeue() {
        if (head == null) return null;
        int value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }
    //遍历
    public void printAll() {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }
}
