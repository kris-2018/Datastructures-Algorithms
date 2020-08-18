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


}
