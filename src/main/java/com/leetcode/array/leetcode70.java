package com.leetcode.array;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
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
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 *
 *
 * 1    1
 * 2    2
 * 3    3(1 1, 1 2, 2 1) |  f(2) + f(1)
 * 4    f(2) + f(3) |跨两步，跨一步
 * 找最近重复字问题: if else  for while  recursion
 * n级台阶，只能从n - 1阶走 或者从 n - 2阶走
 *
 * 归纳法： f(n) = f(n-2) + f(n-1) | fibonacci斐波拉契数列
 * 解法：递归 2n^2
 *      循环  for i = 1; i <= n; ++i{ //用一个数组
 *          a[i] = a[i-1] + a[i-2];
 *      }
 *
 */
public class leetcode70 {

    public static void main(String[] args) {
        leetcode70 leetcode70 = new leetcode70();
        System.out.println(leetcode70.climbStairs(4));
    }

    /**
     * 归纳   时间复杂度O(n)
     * @param n
     * @return
     */
    public int climbStairs(int n){
            if (n <= 2 ) return n;
            int f1 = 1, f2 = 2, f3 = 3; //保存最近的3个值，然后不断的往前累加;
            for (int i = 3; i < n + 1; i++){
                f3 = f1 + f2;
                f1 = f2;
                f2 = f3;
            }
            return f3;
    }
}
