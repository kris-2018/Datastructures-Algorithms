package com.leetcode.algorithm.string.operatequestion;

/**
 *
 * https://leetcode-cn.com/problems/reverse-string
 *
 */
public class LeetCode344ReverseString {
    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);

    }
    public static void reverseString(char[] s) {
        if (s == null) return;
        //头指针 尾指针 一个++ 一个 --
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }

}
