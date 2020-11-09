package com.leetcode.datastructure.queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 232. 用栈实现队列
 * 使用栈实现队列的下列操作：
 * <p>
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * <p>
 * <p>
 * 示例:
 * <p>
 * MyQueue queue = new MyQueue();
 * <p>
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * <p>
 * <p>
 * 说明:
 * <p>
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * <p>
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * 时间复杂度(向栈压入元素) 入队O(1)
 *         出队 -- 摊还复杂度 O(1), 最坏情况下output空 O(n), 最好情况是O(1) 即output非空,就不需要将input中的数据搬迁到output中;
 */
public class LeetCode232ImplementQueueUsingStacks {

    public static void main(String[] args) {
        LeetCode232ImplementQueueUsingStacks queue = new LeetCode232ImplementQueueUsingStacks();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println("push入栈, peek查看栈顶元素, 利用2个栈实现队列先进先出: ");
        System.out.println(queue.peek());
        queue.pop();
        System.out.println(queue.peek());
        System.out.println(queue.empty());
    }

    Deque<Integer> input = new LinkedList<>();  //入队
    Deque<Integer> output = new LinkedList<>(); //出队
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
        if (output.isEmpty())
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        return output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}
