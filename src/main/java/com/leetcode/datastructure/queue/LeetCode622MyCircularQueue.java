package com.leetcode.datastructure.queue;

/**
 *
 * 循环队列的三种实现方式： 1、牺牲一个存储空间。 2、布尔标记法。 3、计数器法。
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * 你的实现应该支持如下操作：
 *      MyCircularQueue(k): 构造器，设置队列长度为 k 。
 *      Front: 从队首获取元素。如果队列为空，返回 -1 。
 *      Rear: 获取队尾元素。如果队列为空，返回 -1 。
 *      enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 *      deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 *      isEmpty(): 检查循环队列是否为空。
 *      isFull(): 检查循环队列是否已满。
 *  
 *
 * 示例：
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 *  
 *
 * 提示：
 *
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-circular-queue
 *
 */
public class LeetCode622MyCircularQueue {
    public static void main(String[] args) {
        LeetCode622MyCircularQueue circularQueue = new LeetCode622MyCircularQueue(3);
        System.out.println("入队: " + circularQueue.enQueue(1));
        System.out.println("入队: " + circularQueue.enQueue(2));
        System.out.println("入队: " + circularQueue.enQueue(3));
        System.out.println("入队: " + circularQueue.enQueue(4));

        System.out.println("队尾元素:" + circularQueue.rear());
        System.out.println("是否已满:" + circularQueue.isFull());

        System.out.println("出队: " + circularQueue.deQueue());
        System.out.println("入队: " + circularQueue.enQueue(4));
        System.out.println("队尾元素:" + circularQueue.rear());

    }
    private int front;
    private int rear;
    private int capacity; //arr数组的大小, 它要比循环队列大一个
    private int[] arr; //循环队列底层 arr数组


    /** Initialize your data structure here. Set the size of the queue to be k. */
    public LeetCode622MyCircularQueue(int k) {
        capacity = k + 1;
        arr = new int[capacity];
        // 在 front 出队，故设计在数组的头部，方便删除元素
        // 删除元素的时候，只索引 +1（注意取模）
        // 在 rear 入队，故设计在数组的尾部，方便插入元素
        // 插入元素的时候，先赋值，后索引 +1（注意取模）

        front = 0;
        rear = 0;
    }

    /** 入队, 下标以rear, rear下标占一个空间不存储数据  Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        arr[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }
    /** 出队 下标以front Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity;
        return true;
    }

    private boolean isEmpty() {
        return front == rear;
    }
    private boolean isFull() {
        return (rear + 1) % capacity == front;
    }
    /* Get the front item from the queue.*/
    public int front() {
        if (isEmpty()) return -1;
        return arr[front];
    }
    /* Get the last item from the queue.*/
    public int rear() {
        if (isEmpty()) return -1;
        return arr[(rear - 1 + capacity) % capacity];
    }
}
