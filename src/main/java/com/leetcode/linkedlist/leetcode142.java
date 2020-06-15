package com.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 */
public class leetcode142 {

    public static void main(String[] args) {
        /** 插入数据 */
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);

        node1.setNext(node2);
        node2.setNext(node4);
        node4.setNext(node1);

        System.out.println("\n链表带环的节点为: ");
        System.out.println((detectCycle(node1)));
    }

    /**
     * 方法一
     * 双指针解法
     * 时间复杂度 O(n)  空间复杂度 O(1)
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null; // no circle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) { //they collided, have cycle linkedlist
                while (head != fast) {
                    fast = fast.next;
                    head = head.next;
                }
                return head;
            }
        }
        return null; // no circle
    }


    /**
     * 方法二
     * 时间复杂度 O(n)  空间复杂度 O(n)
     * HashSet法，不断将节点放进set中，若是已存在，则肯定是入环的第一个节点，将该节点返回即可
     * @param head
     * @return
     */
    public static ListNode detectCycle1(ListNode head) {
        Set nodes = new HashSet();
        //ListNode current = head;
        while (head != null) {
            if (nodes.contains(head))
                return head;
            nodes.add(head);
            head = head.next;
        }
        return null;
    }
}
