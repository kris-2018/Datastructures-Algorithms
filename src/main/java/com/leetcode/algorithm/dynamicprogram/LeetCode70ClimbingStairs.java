package com.leetcode.algorithm.dynamicprogram;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * https://leetcode-cn.com/problems/climbing-stairs/description/
 *
 *  递推公式 DP方程 F(n) = F(n - 1) + F(n - 2)
 *  变形:
 *      1. 1 2 3  (它可以上1级  2级  3级)
 *      2. 还是1 2 3 如果相邻两步的步伐不能相同 , 就不能走 1 1 1了，
 */
public class LeetCode70ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs3(5));
    }

    /**
     * 辅助缓存
     * 定义三个变量，用于临时存储斐波那契数列计算的三个数值，然后，依次循环遍历，得到最终的结果
     * 需要注意的是，每次运行，求出该斐波那契数列某一个数值时，需要为下一次计算做准备，故需要将此次的数值赋值，用于下次计算，如 f1 = f2和 f2 = f3
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param n
     * @return
     */
    public static int climbStairs3(int n) {
        if (n <= 2) return n;
        int f1 = 1, f2 = 2, f3 = 3;
        for (int i = 3; i <= n; i++) {
            f3 = f2 + f1;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }
    /**
     * 辅助数组
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 单纯递归
     * 时间复杂度 O(2^n)
     * 空间复杂度 O(n)
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
