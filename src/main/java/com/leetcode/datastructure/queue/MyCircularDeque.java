package com.leetcode.datastructure.queue;

/**
 * 641. 设计循环双端队列    Design Circular Deque
 * front
 * leetcode641
 * https://leetcode.com/problems/design-circular-deque/
 */
public class MyCircularDeque {

    public static void main(String[] args) {
        MyCircularDeque myCircularDeque = new MyCircularDeque(3);
        System.out.println("从头部插入数据: " + myCircularDeque.insertFront(1));
        System.out.println("从头部插入数据: " + myCircularDeque.insertFront(2));
        System.out.println("从头部插入数据: " + myCircularDeque.insertFront(3));

//        System.out.println("从尾部插入数据: " + myCircularDeque.insertLast(1));
//        System.out.println("从尾部插入数据: " + myCircularDeque.insertLast(2));
//        System.out.println("从尾部插入数据: " + myCircularDeque.insertLast(3));
//        System.out.println("从尾部插入数据: " + myCircularDeque.insertLast(0));

        //System.out.println(myCircularDeque.isFull());
        //System.out.println(myCircularDeque.isEmpty());

/*        System.out.println("从尾部删除数据: " + myCircularDeque.deleteFront());
        System.out.println(myCircularDeque.deleteFront());*/

//         System.out.println("从尾部删除数据: " + myCircularDeque.deleteLast());
//         System.out.println("从尾部删除数据: " + myCircularDeque.deleteLast());
//         System.out.println("从尾部删除数据: " + myCircularDeque.deleteLast());

        for (int a : myCircularDeque.arr) {
            System.out.print(a + " ");
        }

        System.out.println("\n获取头指针的元素:" + myCircularDeque.getFront());
        System.out.println("获取尾指针的元素:" + myCircularDeque.getRear());
    }

    private int[] arr;
    private int capacity = 0; //数组大小capacity,要比循环队列的容量k大一位
    private int front = 0; //队头下标
    private int rear = 0; //队尾下标

    public MyCircularDeque(int k) {
        arr = new int[capacity];
        capacity = k + 1;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     * 从 front 头指针(从后往前插入) 添加数据
     * front = (front - 1 + capacity)  % capacity;
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        front = (front - 1 + capacity)  % capacity; //front元素的下标, 先移动后插入数据, 从数组的最后一个位置插入往前
        arr[front] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     * 从 rear 尾指针(从前往后) 添加数据   尾部指针: rear = (rear + 1) % capacity
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        arr[rear] = value;
        rear = (rear + 1) % capacity; //找到尾指针所在下标
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     * 双端队列, 也可以从 front 头指针出
     * 删除一个, front从前往后移动一个
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     * 从尾 rear 指针处往前删除
     * 从尾删除一个, rear前移一个即可,arr数组中并没有移除这个元素, 之后插入时替换即可。
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    /**
     * Get the front item from the deque.
     * 获取头指针所在的元素
     */
    public int getFront() {
        return isEmpty() ? -1 : arr[front];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        return isEmpty() ? -1 : arr[(rear - 1 + capacity) % capacity];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * Checks whether the circular deque is full or not.
     * 循环队列满的条件是 (rear + 1) % capacity == front
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}
