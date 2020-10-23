package com.leetcode.datastructure.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
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



public class LeetCode25ReverseNodesInKGroup {

    public static void main(String[] args) {
        /*插入数据*/
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        /*原始链表*/
        ListNode node = node1;
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
        System.out.println("\n Reverse Nodes in k-Group");
        node = reverseKGroup(node1, 2);
        while (node != null){
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
    }


    public static ListNode reverseKGroup(ListNode head,int k) {
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
     * 递归的方法
     * Through the way recursive to calculation
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup2(ListNode head,int k){
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k){  // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k){ // if k+1 node is found
            curr = reverseKGroup2(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count --> 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head;  // move head of reversed part to a new node
                head = tmp;  // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head; // 0 != 4  ---> while(count -->0)?
    }

    /**
     * 栈
     * 用栈，我们把 k个数压入栈中，然后弹出来的顺序就是翻转的！
     * 注意
     * 1.剩下的链表个数够不够 k 个（因为不够 k 个不用翻转）；
     * 2.已经翻转的部分要与剩下链表连接起来。
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup3(ListNode head, int k) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (true) {
            int count = 0;
            ListNode node = head;
            while (node != null && count < k) {
                stack.add(node);
                node = node.next;
                count++;
            }
            if (count != k) {
                current.next = head;
                break;
            }
            while (!stack.isEmpty()) {
                current.next = stack.pollLast();
                current = current.next;
            }
            current.next = node;
            head = node;
        }
        return dummy.next;
    }

    public static ListNode reverseKGroup4(ListNode head, int k) {
        ListNode begin;
        if (head == null || head.next == null || k == 1) return head;
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        begin = dummyhead;
        int i = 0;
        while (head != null) {
            i++;
            if (i%k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyhead.next;
    }
    public static ListNode reverse(ListNode begin, ListNode end){
        ListNode curr = begin.next;
        ListNode next, first;
        ListNode prev = begin;
        first = curr;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }

    public static ListNode reverseKGroup5(ListNode head,int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pointer = dummy;
        while (pointer != null) {
            ListNode node = pointer;
            // first check whether there are k nodes to reverse
            for (int i = 0; i < k && node != null; i++) node = node.next;
            if (node == null) break;
            // now we know that we have k nodes, we will start from the first node
            ListNode prev = null, curr = pointer.next, next = null;
            for (int i = 0; i < k; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            ListNode tail = pointer.next;
            tail.next = curr;
            pointer.next = prev;
            pointer = tail;
        }
        return dummy.next;
    }

}

























