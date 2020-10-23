package com.leetcode.datastructure.linkedlist;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
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
        System.out.println(mergeTwoLists2(node1, listNode1));// 1 1 2 3 4 4

    }

    /**
     * 方法一
     * 递归调用
     * 合并(l1, l2)  等价于(假设l1更小)  l1.next = 合并(l1.next, l2) ||  取出头结点l1,解决子问题, 合并(l1.next, l2), l1.next指向子问题的结果即可。
     * 识别模式, 子问题和原问题具有相同结构, 考虑自上而下的递归;
     * 时间复杂度 O(M + N)
     * 空间复杂度 O(M + N), 递归需要叠加栈
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //处理边界条件
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        //递归调用
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
     * 迭代 暴力解法
     * 当 l1 和 l2 都不为空时，判断l1 和l2哪一个链表的头节点的值更小，将较小值的节点添加到结果里，当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。
     *
     * 时间复杂度 O(M + N)
     * 空间复杂度 O(1)
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); //哨兵节点 dummy, 可以在最后比较容易地返回合并后的链表;
        ListNode current = dummy; //维护一个 current 指针，比较大小然后调整它的 next 指针
        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        // 合并后 l1 和 l2 最多只有一个还未被合并完，将非空链表接在合并链表的后面，并返回合并链表即可
        current.next = (l1 == null ? l2 : l1);
        return dummy.next;
    }
}
