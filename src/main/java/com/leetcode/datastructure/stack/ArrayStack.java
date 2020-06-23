package com.leetcode.datastructure.stack;
/**
 * 基于数组实现的顺序栈
 * push 入栈
 * pop 出栈
 */

public class ArrayStack {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push("1");
        System.out.println(arrayStack.count);

    }

    private String[] items;
    private int count; //栈中元素个数
    private int n;     //栈的大小

    /** 初始化数组，申请一个大小为 n 的数组空间 */
    public ArrayStack(int n) {
        this.items = new String[n];
        this.count = 0;
        this.n = n;
    }

    /** 入栈 */
    public boolean push(String item) {
        if (count == n) return false;
        items[count] = item;
        ++count;
        return true;
    }
    /** 出栈 */
    public String pop() {
        if (count == 0) return null;
        String tmp = items[count - 1];
        --count;
        return tmp;
    }

}
