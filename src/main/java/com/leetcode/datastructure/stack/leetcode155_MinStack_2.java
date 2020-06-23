package com.leetcode.datastructure.stack;

import java.util.Stack;

/**
 * 用两个栈, 一个维护出入关系, 一个维护最小的栈
 * 有2个栈可以解决很多特殊性的数据结构
 * 还有一类面试题是
 * 只用队列来实现栈 --> 用两个队列，每次先进先出的时候都去到另外一个队列再出去 或者
 * 用栈来实现队列 --> 用两个栈, 每次先进后出的时候就压到另外一个栈去, 把它的顺序再倒腾一遍。
 *
 */
public class leetcode155_MinStack_2 {

    private Stack<Integer> stack;
    private Stack<Integer> min_stack;

    public leetcode155_MinStack_2() {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }
    public void push(int x) {
        stack.push(x);
        if (min_stack.isEmpty() || x <= min_stack.peek()) {
            min_stack.push(x);
        }
    }
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
