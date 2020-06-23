package com.leetcode.datastructure.linkedlist;

import lombok.Builder;
import lombok.Data;

/**
 * 实现链表的增删改查
 * 实现链表的基本功能：增删改查
 */
@Data
@Builder
public class SingleLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;

    public SingleLinkedList() {
    }

    public SingleLinkedList(ListNode head, ListNode tail) {
        this.head = head;
        this.tail = tail;
    }

    public SingleLinkedList(ListNode head, ListNode tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }


    /**
     * 每次在链表尾部插入
     * @param data
     */
    public void addTailNode(int data) {
        if (head == null) {
            head = new ListNode(data, null);
            tail = head;
        } else {
            ListNode node = new ListNode(data, null);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * 每次在链表头部插入
     * @param data
     */
    public void addHeadNode(int data) {
        head = new ListNode(data, head);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    /**
     * 根据节点位置查询该节点
     * @param index
     * @return
     */
    public ListNode findNodeByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("链表越界啦");
        }
        ListNode current = head;
        int i = 0;
        while (current != null) {
            if (i == index) {
                break;
            }
            current = current.next;
            i++;
        }
        return current;
    }
    /**
     * 在指定位置的前边插入新节点数据
     * @param data
     * @param index
     */
    public void addIndexNode(int data, int index) {
        if (head == null) {
            addTailNode(data);
        }else {
            if (index == 0) {
                addHeadNode(data);
            }else {
                ListNode pre = findNodeByIndex(index-1);//找到要插入位置的前一个节点
                ListNode node = new ListNode(data, pre.next);
                //插入后pre的next指向新节点，新节点的next指向原来pre的下一个节点
                pre.next = node;
                size++;
            }
        }

    }

    @Override
    public String toString() {
        ListNode node = head;
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.data + " ");
            node = node.getNext();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addTailNode(1);
        singleLinkedList.addTailNode(2);
        singleLinkedList.addHeadNode(3);
        System.out.println("链表的遍历: " + singleLinkedList.toString());
        singleLinkedList.addIndexNode(5,2);
        System.out.println("链表的遍历: " + singleLinkedList.toString());
        System.out.println("链表的长度为: " + singleLinkedList.getSize());
        System.out.println(singleLinkedList.findNodeByIndex(0));

    }


}
