package com.leetcode.algorithm.greedy;

/**
 * 402. 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * https://leetcode-cn.com/problems/remove-k-digits/
 */
public class LeetCode402RemoveKDigits {

    public static void main(String[] args) {
        //String num = "1432219"; //int k = 3;
        //String num = "10200";
        String num = "10";
        int k = 2;

        System.out.println(removeKdigits(num, k));
    }

    /**
     * 时间复杂度 O(n)
     * @param str
     * @param k
     * @return
     */
    public static String removeKdigits(String str, int k) {
        int digits = str.length() - k;
        char[] stack = new char[str.length()];
        int top = 0;
        // k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one then removing it will get a smaller number
            // but we can only do so when k is larger than 0
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stack[top++] = c;
        }
        //判断char[] stack中首位是否为 0
        int index = 0;
        while (index < digits && stack[index] == '0')
            index++;
        String strs = index == digits ? "0" : new String(stack, index, digits - index);
        return strs;
    }

}
