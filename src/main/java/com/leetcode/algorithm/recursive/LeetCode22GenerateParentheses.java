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
        for (Object o : generateParenthesis(2).toArray()) {
            System.out.println(o);
        }
    }


    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    private static void backtrack(List<String> list, String str, int open, int close, int max) {
        //1. 递归结束条件
        if (str.length() == max * 2) { //() 成对出现
            list.add(str);
            return;
        }
        if (open < max) //open 左括号
            backtrack(list, str+"(", open + 1, close, max);
        if (close < open) //close 右括号
            backtrack(list, str+")", open, close + 1, max);

    }
}
