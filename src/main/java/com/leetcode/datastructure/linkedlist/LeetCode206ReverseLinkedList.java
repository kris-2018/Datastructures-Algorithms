//反转一个单链表。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL
//
// 进阶:
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
package com.leetcode.datastructure.linkedlist;

/**
 * 206. 反转链表 Reverse Linked List
 * 反转一个单链表。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * https://leetcode.com/problems/reverse-linked-list/
 *
 */
public class LeetCode206ReverseLinkedList {
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
        /*反转后的链表*/
        System.out.println("\n反转:");
        node = reverseList(node1);
        while (node != null){
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }

    }

    /**
     * 单链表的反转  iterative solution
     * 3个指针 prevHead, head, recordNext
     * 时间复杂度 O(n), 空间复杂度O(1)
     * @param head
     * @return
     * prevHead, head, recordNext. Everytime in loop just make head. next points to prevHead,
     *               and then move all these three pointers to one next step.
     * Since when we exit the while loop, head is pointing to null,
     *            so prevHead points to the end node of original list, and thus we return prevHead.
     *        1->2->3->4->null
     * null<-1<-2<-3<-4
     *
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prevHead = null; //
        while (head != null) {
            ListNode recordNext = head.next; //recordNext 只记录下一个结点
            head.next = prevHead; //head: 1->null  => 2->1->null  =>  3->2->1->null => 4->3->2->1->null
            prevHead = head;   //preHead: 1->null  => 2->1->null  =>  3->2->1->null => 4->3->2->1->null
            head = recordNext;    //head: 2->3->4->null =>  3->4->null  => 4->null  将recordHead指向 head,准备下次loop
        }
        return prevHead;
    }

    /**
     * 递归方法
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head){
        /* recursive solution 递归调用 */
        return reverseListInt(head, null);
    }
    public static ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null){
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }

}