package com.leetcode.algorithm.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 */
public class LeetCode22GenerateParentheses {

    public static void main(String[] args) {
        for (Object o : generateParenthesis(3).toArray()) {
            System.out.println(o);
        }
    }


    /**
     *  回溯算法
     *  The idea here is to only add '(' and ')' that we know will guarantee us a solution ( instead of adding 1 too many close).
     *  Once we add a '(' we will then discard it and try a ')' which can only close a valid '('. Each of these steps are recursively called.
     *  choose between left and right parenthesis
     *  In backtrack function, list all your options and try each one of them out.
     *  As for this problem, every time you choose a "(" means making another ")" available.
     *  时间复杂度为 ???
     *  空间复杂度 ？？
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    /** open左括号, close右括号 */
    private static void backtrack(List<String> list, String str, int left, int right, int n) {
        /** 1. 递归结束条件 str长度 = 对数的2倍 */
        if (str.length() == n * 2) { // () 成对出现
            list.add(str);
            return;
        }
        /** 2. 分解为两个字问题 conquer subproblems  3. 下探到下一层 drill down */
        //先满足左边为n个, 再迭代右边 递归栈调用,进去到底时要出去
        if (left < n)
            backtrack(list, str+"(", left + 1, right, n);
        //左边括号个数 < 右边括号
        if (right < left)
            backtrack(list, str+")", left, right + 1, n);

    }
}
