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
     * @param head
     * @return
     *      //head = ListNode(data=1, next=ListNode(data=2, next=ListNode(data=3, next=ListNode(data=4, next=ListNode(data=5, next=null)))))
     *      //newHead = null
     *      //next = head.next = ListNode(data=2, next=ListNode(data=3, next=ListNode(data=4, next=ListNode(data=5, next=null))))
     * ListNode next = head.next;
     *      //head = ListNode(data=1, next=ListNode(data=2, next=ListNode(data=3, next=ListNode(data=4, next=ListNode(data=5, next=null)))))
     *      //newHead = null
     *      //next = ListNode(data=2, next=ListNode(data=3, next=ListNode(data=4, next=ListNode(data=5, next=null))))
     *      //head.next = ListNode(data=2, next=ListNode(data=3, next=ListNode(data=4, next=ListNode(data=5, next=null))))
     * head.next = newHead;
     *      //head = ListNode(data=1, next=null)
     *      //newHead = null
     *      //next = ListNode(data=2, next=ListNode(data=3, next=ListNode(data=4, next=ListNode(data=5, next=null))))
     *     //head.next = null
     * newHead = head;
     *      //head = ListNode(data=1, next=null)
     *      //newHead = ListNode(data=1, next=null)
     *      //next = ListNode(data=2, next=ListNode(data=3, next=ListNode(data=4, next=ListNode(data=5, next=null))))
     * head = next;
     */
    public static ListNode reverseList(ListNode head) {
        ListNode newHead = null; //新链表
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

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