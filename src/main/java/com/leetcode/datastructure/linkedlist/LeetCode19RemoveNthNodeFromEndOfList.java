package com.leetcode.datastructure.linkedlist;

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

        System.out.println(removeNthFromEnd2(node1, 2));
    }

    /**
     * 一次遍历
     * 两个指针被 n 个结点分开
     * 时间复杂度为 O(L) L为结点个数 , 空间复杂度为O(1)
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode fast = start, slow = start;
        start.next = head;
        //fast指针移动到倒数 n位, Advances fast pointer so that the gap between fast and slow is n nodes apart
        for (int i = 1; i <= n+1; i++) {
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
}
