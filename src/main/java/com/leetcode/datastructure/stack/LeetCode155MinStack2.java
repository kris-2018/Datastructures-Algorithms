package com.leetcode.datastructure.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作, 并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 用两个栈, 一个维护出入关系, 一个维护最小的栈
 * 有2个栈可以解决很多特殊性的数据结构
 * push、pop、top、getMin的时间复杂度都为 O(1)
 * 空间复杂度为 O(n)
 *
 * https://leetcode-cn.com/problems/min-stack/
 *
 * 还有一类面试题是
 * 只用队列来实现栈 --> 用两个队列，每次先进先出的时候都去到另外一个队列再出去 或者
 * 用栈来实现队列 --> 用两个栈, 每次先进后出的时候就压到另外一个栈去, 把它的顺序再倒腾一遍。
 *
 */
public class LeetCode155MinStack2 {
    public static void main(String[] args) {
        LeetCode155MinStack2 minStack2 = new LeetCode155MinStack2();
        minStack2.push(4);
        minStack2.push(2);
        minStack2.push(3);
        minStack2.push(1);
        System.out.println(minStack2.top());
        minStack2.pop();
        System.out.println(minStack2.top());
        System.out.println(minStack2.getMin());

    }

    private Deque<Integer> stack;
    private Deque<Integer> min_stack;
    public LeetCode155MinStack2() {
        stack = new LinkedList<>();
        min_stack = new LinkedList<>();
    }
    public void push(int x) {
        stack.push(x);
        if (min_stack.isEmpty() || x <= min_stack.peek()) {
            min_stack.push(x);
        }
    }
    /*如果stack.pop() 中与 min_stack.peek()相等, min_stack中也要删除 */
    public void pop() {
        if (stack.pop().equals(min_stack.peek()))
            min_stack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return min_stack.peek();
    }
}
