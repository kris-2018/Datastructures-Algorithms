package com.leetcode.algorithm.recursive;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * 递推公式:
 * f(n) = f(n-1)+f(n-2)
 * 终止条件 f(1) = 1, f(2) = 2
 * https://leetcode-cn.com/problems/climbing-stairs/
 * https://leetcode.com/problems/climbing-stairs/discuss/25403/Java-recursive-solution-and-dp-solution
 */
public class LeetCode70ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

    /**
     * 递归调用, 调用一次压栈, 先进后出, 后进先出
     * 不理解的可以尝试画出递归树帮助理解递归的进进出出, debugger
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int i = climbStairs(n - 1);
        int j = climbStairs(n - 2);
        int result = i + j;
        return result;
    }

    /** 见 com.leetcode.datastructure.array.LeetCode70ClimbingStairs  */

}
