package com.leetcode.datastructure.linkedlist;

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

    /**
     * 方法一
     * 迭代法, 时间复杂度 O(n), n为链表中节点的数量;  空间复杂度 O(1)
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head){
        //第一轮循环中 dummy 作为head节点的前驱结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            //nodes to be swapped
            ListNode first = current.next; //1->2->3->4
            ListNode second = current.next.next; //2->3->4
            //swapping
            first.next = second.next; //此步从first {1->2->3->4}  ==>  {1->3->4} ,同时 head和current也就变成了 0->1->3->4
            current.next = second; //此步current变成 0->2->3->4
            current.next.next = first;//此步current变成 0->2->1->3->4
            //reinitializing and for the next swap
            current = current.next.next; //此步current变成 1->3->4
        }
        return dummy.next;
    }

    /**
     *  方法二
     *  递归
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        return head;
    }
}
