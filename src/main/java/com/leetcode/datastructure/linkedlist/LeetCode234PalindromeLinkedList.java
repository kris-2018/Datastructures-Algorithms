package com.leetcode.datastructure.linkedlist;

/**
 * 234. 回文链表  Palindrome Linked List
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class LeetCode234PalindromeLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);


        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        System.out.println(isPalindrome(node1));
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;
        while (slow != null) {
            if (fast.data != slow.data) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
    /* 反转 */
    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) return true;
        // reverse the first portion during iteration
        ListNode fast = head, newHead = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        // skip the node at central place if the list contains odd number of nodes
        if (fast != null)
            head = head.next;
        while (newHead != null && head != null) {
            if (newHead.data != head.data)
                return false;
            newHead = newHead.next;
            head = head.next;
        }
        return true;
    }
}
