package com.leetcode.datastructure.queue;

/**
 * 641. 设计循环双端队列    Design Circular Deque
 *
 * leetcode641
 * https://leetcode.com/problems/design-circular-deque/
 */
public class MyCircularDeque {

    public static void main(String[] args) {
        MyCircularDeque myCircularDeque = new MyCircularDeque(3);
        System.out.println(myCircularDeque.insertFront(1));
        System.out.println(myCircularDeque.insertLast(2));

        //System.out.println(myCircularDeque.isFull());
        //System.out.println(myCircularDeque.isEmpty());

        // System.out.println(myCircularDeque.deleteLast());
        System.out.println(myCircularDeque.deleteFront());

        for (int a : myCircularDeque.arr) {
            System.out.print(a + " ");
        }

        System.out.println("\n获取头指针的元素:" + myCircularDeque.getFront());
        System.out.println("获取尾指针的元素:" + myCircularDeque.getRear());
    }

    private int[] arr;
    private int n = 0; //数组大小
    private int front = 0; //队头下标
    private int rear = 0; //队尾下标

    public MyCircularDeque(int nacity) {
        arr = new int[nacity];
        n = nacity;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     * 从 front 头指针 添加数据  (front + 1) % n
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        arr[front] = value;
        front = (front + 1) % n;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     * 从 rear 尾指针 添加数据   尾部指针: (rear - 1 + n) % n
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        rear = (rear - 1 + n) % n; //找到尾指针所在下标
        arr[rear] = value;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     * 双端队列, 也可以从 front 头指针出
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        front = (front - 1 + n) % n;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     * 从尾 rear 指针出
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        rear = (rear + 1) % n;
        return true;
    }

    /**
     * Get the front item from the deque.
     * 获取头指针所在的元素
     */
    public int getFront() {
        return isEmpty() ? -1 : arr[(front - 1 + n) % n];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        return isEmpty() ? -1 : arr[rear];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * Checks whether the circular deque is full or not.
     * arr 的 capacity > 1 不空 , rear 占一位
     */
    public boolean isFull() {
        return (front + 1) % n == rear;
    }
}
