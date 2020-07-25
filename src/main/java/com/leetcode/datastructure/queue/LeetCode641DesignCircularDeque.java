package com.leetcode.datastructure.queue;

/**
 * 641. 设计循环双端队列
 * 设计实现双端队列, 你的实现需要支持以下操作：
 * MyCircularDeque(k)：构造函数,双端队列的大小为 k 。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 *
 *
 *
 * 提示：
 * 所有值的范围为 [1, 1000]
 * 操作次数的范围为 [1, 1000]
 * 请不要使用内置的双端队列库。
 *
 * https://leetcode.com/problems/design-circular-deque
 *
 */
public class LeetCode641DesignCircularDeque {
    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(4);
        circularDeque.insertFront(1);
        circularDeque.insertLast(2);

    }


}
