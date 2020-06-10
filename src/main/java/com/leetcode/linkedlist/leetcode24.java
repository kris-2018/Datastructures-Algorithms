package com.leetcode.linkedlist;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * https://leetcode.com/problems/swap-nodes-in-pairs
 *
 */
public class leetcode24 {

    public static void main(String[] args) {
        /*插入数据*/
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        /*原始链表*/
        ListNode node = node1;
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
        System.out.println("\nSwap Nodes in Pairs");
        /** Swap Nodes in Pairs */
        node = swapPairs(node1);
        while (node != null){
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
    }

    public static ListNode swapPairs(ListNode head){
        //dummy node 作为head节点的前驱结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            //nodes to be swapped
            ListNode first = current.next;
            ListNode second = current.next.next;
            //swapping
            first.next = second.next;
            current.next = second;
            //reinitializing and for the next swap
            current.next.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }
}
