package com.leetcode.algorithm.binarysearch;

/**
 * 367. Valid Perfect Square
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Follow up: Do not use any built-in library function such as sqrt.
 *
 *
 *
 * Example 1:
 * Input: num = 16
 * Output: true
 * Example 2:
 *
 * Input: num = 14
 * Output: false
 *
 * Constraints:
 * 1 <= num <= 2^31 - 1
 *
 *  https://leetcode.com/problems/valid-perfect-square/
 *
 *
 * This is a math problem：
 * 1 = 1
 * 4 = 1 + 3
 * 9 = 1 + 3 + 5
 * 16 = 1 + 3 + 5 + 7
 * 25 = 1 + 3 + 5 + 7 + 9
 * 36 = 1 + 3 + 5 + 7 + 9 + 11
 * ....
 * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
 *
 *
 */
public class LeetCode367ValidPerfectSquare {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(81));
    }

    /**
     * 二分查找 time complexity is O(log(n))
     * One thing to note is that we have to use long for mid to avoid mid*mid from overflow.
     * Also, you can use long type for left and right to avoid type casting for mid from long to int.
     * @param num
     * @return
     */
    public static boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;
        while (left <= right) {
            long mid = (left + right) >>> 1;
            if (mid * mid == num) return true;
            else if (mid * mid < num)
                left = (int) mid + 1;
            else
                right = (int) mid - 1;
        }
        return false;
    }

    /**
     * 牛顿迭代法 Newton Method
     *
     * @param num
     * @return
     */
    public static boolean isPerfectSquare2(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }

}
