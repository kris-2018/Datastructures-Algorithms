package com.leetcode.linkedlist;

import java.util.HashSet;

/**
 * 141. 环形链表  Linked List Cycle
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class leetcode141 {

    public static void main(String[] args) {

        /** 插入数据 */
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);

        node1.setNext(node2);
        node2.setNext(node4);
        //node4.setNext(node1);

        /** 原始链表的遍历, 环状链表遍历会是一个死循环 1 2 4 1 2 4 1 2 4... */
/*        ListNode node = node1;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.getNext();
        }*/

        System.out.println("\n 链表是否有环");
        System.out.println(hasCycle(node1));
    }

    /** 方法一: 双指针  - 快慢 时间复杂度 O(n)  空间复杂度 O(1)
     * 1. Use two pointers, walker and runner.
     * 2. Walker moves step by step. runner moves two steps at time.
     * 3. If the Linked List has a cycle walker and runner will meet at some point.
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head){
        if (head == null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null){
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) return true;
        }
        return false;
    }


    /**
     * 方法二: 哈希表  时间复杂度 O(n)  空间复杂度 O(n)
     * 检查一个结点此前是否被访问过来判断链表是否为环形链表
     * 遍历所有结点并在哈希表中存储每个结点的引用。
     * 如果当前结点为空结点 null（即已检测到链表尾部的下一个结点），那么我们已经遍历完整个链表，并且该链表不是环形链表。
     * 如果当前结点的引用已经存在于哈希表中，那么返回 true（即该链表为环形链表）。
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head){
        HashSet<ListNode> nodeSet = new HashSet<>();
        while (head != null) {
            if (nodeSet.contains(head)) {
                return true;
            }else{
                nodeSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

}
