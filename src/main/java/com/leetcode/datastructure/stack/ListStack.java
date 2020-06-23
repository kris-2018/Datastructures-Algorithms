package com.leetcode.datastructure.stack;

import com.leetcode.datastructure.linkedlist.ListNode;

/**
 * 用链表实现一个栈
 * 用链表实现栈的先进后出，实现栈的push  pop  peak方法。主要就是利用链表的头结点作为栈顶的元素。
 * 1.当要push的时候，相当于新new一个头结点，然后让新节点指向单链表的头结点。以新节点作为单链表的头节点即可。
 * 2.当要pop的时候，只要将链表的头指针后移到它的next，将next作为新的头结点即可
 * 3.当要peak的时候，只要返回头结点的值就好了。
 *
 */
public class ListStack {

    ListNode header; //头节点
    int elementCount; //栈内元素的个数
    int size; //栈的大小

    /**
     * 通过一个构造函数, 构造一个空的栈
     */
    public ListStack() {
        header = null;
        elementCount = 0;
        size = 0;
    }

    /**
     * 构造一个自定义大小的栈
     */
    public ListStack(int size) {
        header = null;
        elementCount = 0;
        this.size = size;
    }

    public ListNode getHeader() {
        return header;
    }

    public void setHeader(ListNode header) {
        this.header = header;
    }

    public boolean isFull() {
        if (elementCount == size) return true;
        return false;
    }
    public boolean isEmpty() {
        if (elementCount == 0) return true;
        return false;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        if (this.isFull()) {
            throw new RuntimeException("stack is full");
        }
        header = new ListNode(value, header);
        elementCount++;
    }

    /**
     * 出栈
     * @return
     */
    public Object pop() {
        if (elementCount == 0) return new RuntimeException("stack is empty");
        Object object = header.getData();
        header = header.getNext();
        elementCount--;
        return object;
    }

    /**
     * 返回栈顶元素
     * @return
     */
    public Object peek() {
        if (this.isEmpty()) return new RuntimeException("stack is empty");

        return header.getData();
    }

}