package com.leetcode.datastructure.queue;

/**
 *
 * 数组实现   循环队列(单)
 * 该问题使用的数据结构应该是首尾相连的 环。
 * 任何数据结构中都不存在环形结构，但是可以使用一维 数组 模拟，通过操作数组的索引构建一个 虚拟 的环。很多复杂数据结构都可以通过数组实现。
 *
 * 避免了数据搬移操作
 * 确定好队空和队满的判定条件
 * 数组实现 非循环队列，队满的判断条件是 tail == n，队空的判断条件是 head == tail
 *        循环队列 队列为空的判断条件仍然是 head == tail , 队满时的条件为 (tail+1)%n=head
 *               循环队列存的元素个数总是比他占用的存储空间少,队尾指针也需要占用一个位置
 * 从 tail 进, head 出  先进先出
 * https://leetcode-cn.com/problems/design-circular-queue/
 *
 */
public class CircularQueue {

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(3);
        //入队测试
        System.out.println("入队:" + circularQueue.enqueue("a"));
        System.out.println("入队:" + circularQueue.enqueue("b"));
        System.out.println("入队:" + circularQueue.enqueue("c"));

        //遍历循环队列
        circularQueue.printAll();
        //出队测试
        System.out.println("\n出队:" + circularQueue.dequeue() );

    }

    private String[] items; //数组 items
    private int n = 0; //数组大小
    private int head = 0; //表队头下标
    private int tail = 0; //表队尾下标

    /** 申请一个大小为 nacity 的数组 */
    public CircularQueue(int nacity) {
        items = new String[nacity];
        n = nacity;
    }
    //入队
    public boolean enqueue(String item) {
        if ((tail + 1) % n == head ) return false;// (tail + 1) % n == head 即队列满了, tail指针也需要占用一个位置
        items[tail] = item;
        tail = (tail + 1) % n; //入队后 tail 往后 挪动
        return true;
    }

    //出队
    public String dequeue() {
        if (head == tail) return null; // head == tail 队列为空
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }

    /**
     * 遍历循环队列
     */
    public void printAll() {
        for (int i = head; i % n != tail; i++) {
            System.out.print(items[i] + " ");
        }
    }

}
