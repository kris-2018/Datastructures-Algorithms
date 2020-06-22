package com.leetcode.queue;

/**
 *  leetcode641
 *
 */
public class MyCircularDeque {

    int[] arr;
    int front, rear, cap;
    public MyCircularDeque(int capacity) {
        arr = new int[capacity + 1];
        front = 0;
        rear = 0;
        cap = capacity + 1;
    }

    public boolean deleteFront() {
        return true;
    }
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        rear = (rear + 1) % cap;
        return true;
    }
    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty() ? -1 : arr[(front - 1 + cap) % cap];
    }
    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty() ? -1 : arr[rear];
    }
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return front == rear;
    }
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (front + 1) % cap == rear;
    }
}
