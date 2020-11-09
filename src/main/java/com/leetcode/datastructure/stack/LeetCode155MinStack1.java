package com.leetcode.datastructure.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作， 并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 提示：
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 *  https://leetcode.com/problems/min-stack/discuss/49014/Java-accepted-solution-using-one-stack     此方法一个栈, 利用栈存储最小值
 *  https://leetcode.com/problems/min-stack/discuss/49031/Share-my-Java-solution-with-ONLY-ONE-stack 此方法一个栈, 利用差值 store the gap between the min value and the current value;
 *
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 *
 * using one stack
 * 当有更小的值来的时候，只需要把之前的最小值入栈，当前更小的值再入栈即可。
 *  当这个最小值要出栈的时候，下一个值便是之前的最小值了。
 */
public class LeetCode155MinStack1 {

    public static void main(String[] args) {
        LeetCode155MinStack1 minStack_1 = new LeetCode155MinStack1();
        minStack_1.push(4);
        minStack_1.push(2);
        minStack_1.push(3);
        System.out.println(minStack_1.top());
        minStack_1.pop();
        System.out.println(minStack_1.top());
        System.out.println(minStack_1.getMin());

    }

    int min = Integer.MAX_VALUE;
    Deque<Integer> stack = new LinkedList<>();
    /** initialize your data structure here.  */
    public LeetCode155MinStack1() {}
    // stack: MAX_VALUE/4/4/2/3, 每次push一个值时先与上次的最小值min做比较,if(x<min) 就把上次push的min值也push进去。
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    /*删除时,与min值做比较 */
    public void pop() {
        if (stack.pop() == min)
            min = stack.pop();
    }
    /** peek -- top  查看栈顶元素  */
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return min;
    }
}
