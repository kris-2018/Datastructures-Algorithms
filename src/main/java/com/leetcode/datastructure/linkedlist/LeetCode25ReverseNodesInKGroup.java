package com.leetcode.datastructure.linkedlist;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *
 *
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *  https://leetcode.com/problems/reverse-nodes-in-k-group/
 */



public class leetcode25 {

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
        System.out.println("\n Reverse Nodes in k-Group");
        node = reverseKGroup(node1, 4);
        while (node != null){
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
    }


    public static ListNode reverseKGroup2(ListNode head,int k) {
        int n = 0;
        for (ListNode i = head;i != null; n++, i = i.next);
        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for (ListNode prev = dmy, tail = head; n >= k; n -= k){
            for (int i = 1; i < k; i++){
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }
            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }


    /**
     * Through the way recursive to calculation
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head,int k){
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k){
            curr = curr.next;
            count++;
        }
        if (count == k){
            curr = reverseKGroup(curr, k);
            while (count --> 0) {
                ListNode tmp = head.next;
                head.next = curr;
                curr = head;
                head = tmp;
            }
            head = curr;
        }
        return head; // 0 != 4  ---> while(count -->0)?
    }
}

























