package com.leetcode.queue;

/**
 * 用数组实现的队列
 */

public class ArrayQueue {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        System.out.println(arrayQueue.enqueue("a"));
        System.out.println(arrayQueue.enqueue("b"));
        System.out.println(arrayQueue.enqueue("c"));
        for (String item : arrayQueue.items) {
            System.out.print(item + " ");
        }
        System.out.println("\n" +arrayQueue.dequeue("a"));


    }

    private String[] items;
    private int n = 0; //数组大小
    private int head = 0; //队头下标
    private int tail = 0; //队尾下标

    /**
     * 申请一个大小为capacity的数组
     */
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }
    /*    *//** 入队 *//*
    public boolean enqueue(String item) {
        if (tail == n) return false; //队列满了
        items[tail] = item; //放在队尾
        ++tail;
        return true;
    }*/

    /**
     * 入队 将item元素放入队尾
     */
    public boolean enqueue(String item) {
        if (tail == n) { //tail == n 表队尾已满
            if (head == 0) return false; //tail == 0 && head == 0 表整个队列已满
            //数据搬移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            //搬完之后重新更新head和tail
            tail -= head;
            head = 0;
        }
        //队列未满
        items[tail] = item; //放在队尾
        ++tail;
        return true;
    }

    /**
     * 出队 , 会导致类似数组删除操作中 -- 数据不连续
     */
    public String dequeue(String item) {
        if (head == tail) return null;
        String res = items[head];
        ++head;
        return res;

    }
}
