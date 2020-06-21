package com.leetcode.queue;

/**
 * 循环数组实现的队列
 */
public class CircularQueue {

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(4);
        //入栈测试
        System.out.println(circularQueue.enqueue("a"));
        System.out.println(circularQueue.enqueue("b"));
        System.out.println(circularQueue.enqueue("c"));

        for (String item : circularQueue.items) {
            System.out.print(item + " ");
        }
        //出栈测试
        System.out.println("\n" + circularQueue.dequeue() );


    }

    private String[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }
    //入队
    public boolean enqueue(String item) {

        if ((tail + 1) % n == head ) return false;// (tail + 1) % n == head 即队列满了
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    //出队
    public String  dequeue() {
        if (head == tail) return null; // head == tail 队列为空
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }
}
