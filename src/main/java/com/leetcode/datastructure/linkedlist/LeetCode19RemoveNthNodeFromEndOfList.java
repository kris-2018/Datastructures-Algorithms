package com.leetcode.datastructure.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 */
public class LeetCode19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(6);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        System.out.println(removeNthFromEnd(node1, 3));
    }

    /**
     * 一次遍历
     * 两个指针被 n个结点分开
     * 时间复杂度为 O(L) L为结点个数 , 空间复杂度为O(1)
     * 使用两个指针 fast 和 slow 同时对链表进行遍历，且 fast 比 slow超前 n个节点。
     *     当 fast 遍历到链表的末尾时, slow 就恰好处于倒数第 n个节点。
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode fast = head, slow = start;
        start.next = head;
        //fast指针向右移动 n位, Advances fast pointer so that the gap between fast and slow is n nodes apart
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        //fast 和 slow相同的速度移动, 直到fast为null, maintaining the gap
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //删除结点 slow 的后继结点
        slow.next = slow.next.next;
        return start.next;
    }

    /**
     * 两次遍历
     * 第一次遍历找出列表长度L
     * 时间复杂度为 O(L) L为结点个数 , 空间复杂度为O(1)
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        length -= n;
        node = start;
        while (length > 0) {
            length--;
            node = node.next;
        }
        node.next = node.next.next;
        return start.next;
    }

    /**
     * 栈
     * 遍历链表的同时将所有节点依次入栈。根据栈「先进后出」的原则,
     * 弹出栈的第n个节点就是需要删除的节点并且目前栈顶的节点就是待删除节点的前驱节点。
     * 时间复杂度：O(L)，其中 L 是链表的长度。
     * 空间复杂度：O(L)，其中 L 是链表的长度。主要为栈的开销。
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode start = new ListNode(0);
        Deque<ListNode> stack = new LinkedList<>();
        start.next = head;
        ListNode cur = start;
        //遍历链表把各个元素放入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        return start.next;
    }
}
