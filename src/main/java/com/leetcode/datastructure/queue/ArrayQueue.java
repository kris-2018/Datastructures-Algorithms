package com.leetcode.datastructure.queue;

/**
 * 用数组实现的队列
 * 一个问题:
 * 随着不停地进行入队、出队操作，head 和 tail 都会持续往后移动。当 tail 移动到最右边，即使数组中还有空闲空间，也无法继续往队列中添加数据了
 * 解决办法:
 *  在数组那节，也遇到过类似的问题，就是数组的删除操作会导致数组中的数据不连续。我们当的解决方法就是用数据搬移！
 * 数据搬移: 每次出队操作都相当于删除数组下标为 O 的数据，要搬移整个队列中的数据，这样出队操作的时间复杂度就会从原来的 O(1) 变为 O(n)
 * 再次优化: 出队时可以不用搬移数据。如果没有空闲空间了，我们只需要在入队时，再集中触发一次数据的搬移操作。 出队函数 dequeue() 保持不变，改造一下入队函数 enqueue()
 * 队列的 tail 指针移动到数组的最右边后，如果有新的数据入队，就将 head 到 tail 之间的数据，整体搬移到数组中 0 到 tail-head 的位置。
 */

public class ArrayQueue {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        System.out.println(arrayQueue.enqueue("a"));
        System.out.println(arrayQueue.enqueue("b"));
        System.out.println(arrayQueue.enqueue("c"));

        arrayQueue.printAll();
        System.out.println("\n" + arrayQueue.dequeue("a"));


    }

    private String[] items;
    private int n = 0; //数组大小
    private int head = 0; //队头下标
    private int tail = 0; //队尾下标

    /**
     * 申请一个大小为nacity的数组
     */
    public ArrayQueue(int nacity) {
        items = new String[nacity];
        n = nacity;
    }
    /*     /* 入队 /*
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
        //1. tail == n 队列已满
        if (tail == n) {
            if (head == 0) return false; //tail == 0 && head == 0 表整个队列已满
            //数据搬移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            //搬完之后重新更新head和tail
            tail -= head;
            head = 0;
        }
        //2. 队列未满
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

    /**
     * 遍历整个队列
     */
    public void printAll() {
        for (int i = head; i < tail; i++) {
            System.out.print(items[i] + " ");
        }
    }
}
