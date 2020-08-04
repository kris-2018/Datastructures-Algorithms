package com.leetcode.datastructure.queue;

import java.sql.Statement;
import java.util.Stack;

/**
 * 232. 用栈实现队列
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 *
 * 示例:
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 *
 * 说明:
 *
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 */
public class LeetCode232ImplementQueueUsingStacks {

    public static void main(String[] args) {
        LeetCode232ImplementQueueUsingStacks queue = new LeetCode232ImplementQueueUsingStacks();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.peek();
    }
    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();
    public void push(int x) {
        input.push(x);
    }
    public void pop() {
        peek();
        output.pop();
    }
    /**
     * 时间复杂度 O(1)
     * The loop in peek does the moving from input to output stack. Each element only ever gets moved like that once,
     * though, and only after we already spent time pushing it, so the overall amortized cost for each operation is O(1).
     * @return
     */
    private int peek() {
        if (output.empty())
            while (!input.empty()) {
                output.push(input.pop());
            }
        return output.peek();
    }
    public boolean empty() {
        return input.empty() && output.empty();
    }
}
