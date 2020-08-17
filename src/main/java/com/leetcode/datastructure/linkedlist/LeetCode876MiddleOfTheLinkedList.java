package com.leetcode.datastructure.linkedlist;

/**
 * 876. 链表的中间结点
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 *
 * 示例 1：
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 *
 * 示例 2：
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 *  https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class LeetCode876MiddleOfTheLinkedList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        System.out.println(middleNode(node1));
    }

    /**
     * Slow and Fast Pointers 一次遍历
     * Each time, slow go 1 steps while fast go 2 steps.
     * When fast arrives at the end, slow will arrive right in the middle.
     *  时间复杂度：O(N)，其中 N 是给定链表的结点数目。
     * 空间复杂度：O(1)，只需要常数空间存放 slow 和 fast 两个指针。
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 对链表进行两次遍历, 单指针
     *      第一次遍历时，我们统计链表中的元素个数 N,
     *      第二次遍历时，我们遍历到第 N/2 个元素（链表的首节点为第 0 个元素）时，将该元素返回即可。
     * 时间复杂度：O(N) 其中 N是给定链表的结点数目。
     * 空间复杂度：O(1) 只需要常数空间存放变量和指针。
     * @param head
     * @return
     */
    public static ListNode middleNode2(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int k = 0;
        cur = head;
        while (k < n / 2) {
            k++;
            cur = cur.next;
        }
        return cur;
    }
}
