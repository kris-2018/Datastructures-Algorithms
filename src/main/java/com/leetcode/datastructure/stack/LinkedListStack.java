package com.leetcode.datastructure.stack;

import com.leetcode.datastructure.linkedlist.ListNode;
import lombok.Builder;
import lombok.Data;

/**
 * 基于链表实现的栈
 *
 */
@Builder
@Data
public class LinkedListStack {

    private ListNode top = null;
    private int size = 0;

    public void push(int value) {
        ListNode newNode = new ListNode(value, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        size++;
    }

    public int pop() {
        if (top == null) return -1;
        int value = top.data;
        top = top.next;

        if (size > 0) size--;
        return value;

    }

    public void printAll() {
        ListNode p = top;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

}
