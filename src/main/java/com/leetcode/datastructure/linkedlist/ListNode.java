package com.leetcode.datastructure.linkedlist;

import lombok.Builder;
import lombok.Data;

/*在实体类中添加@Data @Builder注解。在需要使用到的地方该类的地方可直接使用get,set方法*/
/**
 * 定义节点
 * 链表由节点构成
 *
 */
@Data
@Builder
public class ListNode {
    public int data;       //data 数据
    public ListNode next; //next指针 指向下一个节点

    public ListNode() {}
    public ListNode(int data) {
        this.data = data;
    }
    public ListNode(int data, ListNode next) {
        this.data = data;
        this.next = next;
    }


/*    public int getData() {
        return data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }*/



}
