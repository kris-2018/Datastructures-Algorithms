package com.leetcode.datastructure.linkedlist;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 *  https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class LeetCode21MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);

        node1.setNext(node2);
        node2.setNext(node4);

        System.out.println("链表1: ");
        ListNode node = node1;
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }

        System.out.println("\n链表2: ");
        ListNode listNode1 = new ListNode(1);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);

        listNode1.setNext(listNode3);
        listNode3.setNext(listNode4);

        ListNode listnode = listNode1;
        while (listnode != null) {
            System.out.print(listnode.getData() + " ");
            listnode = listnode.getNext();
        }


        System.out.println("\n合并后的链表: ");
        System.out.println(mergeTwoLists(node1, listNode1));// 1 1 2 3 4 4

    }

    /**
     * 方法一
     * 递归调用
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.data < l2.data){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 方法二
     * 迭代
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {

        return l2;
    }
}
