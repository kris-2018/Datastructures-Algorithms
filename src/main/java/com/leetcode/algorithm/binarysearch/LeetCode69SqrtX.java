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
        System.out.println(getSqureRoot(8, 6));
    }

    /**
     * 求一个数的平方根
     *
     * @param n：待求的数
     * @param deltaThreshold 误差的阈值
     * @return
     */
    public static double getSqureRoot(int n, double deltaThreshold) {
        double low = 1.0;
        double high = (double) n;
        while (low <= high) {
            double mid = low + ((high - low) / 2);
            double square = mid * mid;
            double delta = Math.abs(square / n - 1);
            if (delta < deltaThreshold) {
                return mid;
            } else if (square < n) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return -1.0;
    }

    //求平方根，可以参考0到99之间猜数字的思路，99换成x, 循环到误差允许内即可，注意1这个分界线。欢迎交流，Java如下
    public static double sqrt(double x, double precision) {
        if (x < 0) {
            return Double.NaN;
        }
        double low = 0;
        double up = x;
        if (x < 1 && x > 0) {
            /** 小于1的时候*/
            low = x;
            up = 1;
        }
        double mid = low + (up - low) / 2;
        while (up - low > precision) {
            if (mid * mid > x) {//TODO mid可能会溢出
                up = mid;
            } else if (mid * mid < x) {
                low = mid;
            } else {
                return mid;
            }
            mid = low + (up - low) / 2;
        }
        return mid;
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
