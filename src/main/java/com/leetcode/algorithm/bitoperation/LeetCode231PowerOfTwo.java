package com.leetcode.algorithm.bitoperation;

/**
 * 231. 2的幂 Power of Two
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 * 输入: 1
 * 输出: true
 * 解释: 20 = 1
 *
 * 示例 2:
 * 输入: 16
 * 输出: true
 * 解释: 24 = 16
 *
 * 示例 3:
 * 输入: 218
 * 输出: false
 */
public class LeetCode231PowerOfTwo {

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
    }

    /**
     * Time complexity = O(1)
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {

        return n > 0 &&  ((n & (n - 1)) == 0); // n & (n - 1) 清零最低位的1, 2^n就满足 ((n & (n - 1)) == 0)
    }

    public static boolean isPowerOfTwo2(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
