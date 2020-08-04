package com.leetcode.datastructure.queue;

import com.sun.deploy.uitoolkit.impl.fx.AppletStageManager;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 225. 用队列实现栈
 * 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 *
 * Example:
 * MyStack stack = new MyStack();
 * stack.push(1);
 * stack.push(2);
 * stack.top();   // returns 2
 * stack.pop();   // returns 2
 * stack.empty(); // returns false
 *
 * 注意:
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 *
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class LeetCode225ImplementStackUsingQueues {
    public static void main(String[] args) {
        LeetCode225ImplementStackUsingQueues stack = new LeetCode225ImplementStackUsingQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.empty());
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.top());

    }

    /** 方法一: 使用两个queue  */
/*
     private Queue queue;
    */
/**
     * push的过程:
     * //queue队列永远只装两个元素,x | queue上一个的嵌套
     *
     *             2
     *           /
     * 3 -- queue        1
     *           \      /
     *            queue
     *                 \
     *                  null queue
     * stored the pointer as an integer to build a nested queue.
     * Each queue just contains one integer for value and one integer for the pointer to next node.
     * push 时间复杂度为 O(1)
     * adding a queue object into another does not copy the entire contents. It is an O(1) operation
     * @param x
     *//*

     public void push(int x) {
         Queue q = new LinkedList();
         q.add(x);
         q.add(queue);
         queue = q;
     }
     public int pop() {
         int remove = (int)queue.remove();
         queue = (Queue) queue.peek();
          return remove;
     }
     public int top() {
         return (int) queue.peek();
     }
     public boolean empty() {
         return queue == null;
     }
*/



     /** 方法二: Only push is O(n), others are O(1). Using one queue
      *
      * Just use a queue where you "push to front" by pushing to back and then rotating the queue until the new element is at the front
      * (i.e., size-1 times move the front element to the back).
      * */
    Queue<Integer> queue;

    public LeetCode225ImplementStackUsingQueues() {
        this.queue = new LinkedList<Integer>();
    }
    public void push(int x) {
        queue.add(x);
        for (int i = 0; i < queue.size() - 1; i++) { //rotate the queue to make the tail be the head
            queue.add(queue.poll()); //poll 与remove的不同之处queue为null时, poll -> null, remove -> throws an exception
        }
    }
    public void pop() {
        queue.poll();
    }
    public int top() {
        return queue.peek();
    }
    public boolean empty() {
        return queue.isEmpty();
    }
}
