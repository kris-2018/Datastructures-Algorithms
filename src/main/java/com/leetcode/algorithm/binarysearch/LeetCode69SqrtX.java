package com.leetcode.algorithm.binarysearch;

/**
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class LeetCode69SqrtX {
    public static void main(String[] args) {
        System.out.println(sqrt(8));
    }

    public static int sqrt(int x) {
        int left = 1;
        int right = x;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (mid > x / mid) {  /* same as mid * mid > x but prevents overflow */
                right = mid - 1;  /* answer <= (mid - 1) */
            } else
                left = mid + 1;
        }
        return right;
    }

    public static int sqrt2(int x) {
        if (x == 0) return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }


}
