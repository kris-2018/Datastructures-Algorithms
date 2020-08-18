package com.leetcode.algorithm.dynamicprogram;

import java.util.Arrays;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 *
 *  https://leetcode-cn.com/problems/unique-paths/
 *
 *  https://www.bilibili.com/video/av53233912?from=search&seid=2847395688604491997
 *
 */
public class LeetCode62UniquePaths {
    public static void main(String[] args) {
        int m = 7, n = 3;
        System.out.println(uniquePaths4(m, n));
    }

    /**
     * 自底向上
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n){
        Integer[][] dp = new Integer[m][n];
        //赋予初始值
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        //开始递推
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1]; //DP 方程
            }
        }
        return dp[m-1][n-1];
    }

    /**
     *  简化操作, 不需要存整个二维数组, 和Fibonacci 数列一样（不需要存从0到n-1这么多中间结果，只需存i-1和i-2即可）
     *  只需存最近的一行（最后一行的1 1 1 ...）,每次从当前这一行往上面垒, 最后得到最上一行的结果, 结果就在n-1的位置
     *  这样就节省了一维的状态存储空间
     *  这两种时间复杂度都是 O(m*n) , 这种方式在内存上更节省
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths2(int m, int n) {
        int[] cur = new int[n]; //表最近的这一行,
        Arrays.fill(cur, 1);//一开始全部都是1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j-1]; //往上累加 cur[j-1]的值
            }
        }
        return cur[n - 1];
    }

    /**
     * Recursive
     * 时间复杂度 O(2^n)
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths3(int m, int n) {
        return uniquePathHelper(m - 1, n - 1);
    }
    private static int uniquePathHelper(int m, int n) {
        if (m < 0 || n < 0) return 0;
        if (m == 0 || n == 0) return 1;
        return uniquePathHelper(m - 1, n) + uniquePathHelper(m, n - 1);
    }

    /**
     * Memoization
     * Time complexity m x n, Space complexity: m x n
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths4(int m, int n) {
        return uniquePathHelper2(m - 1, n - 1, new int[m][n]);
    }
    private static int uniquePathHelper2(int m, int n, int[][] memo) {
        if (m < 0 || n < 0) return 0;
        if (m == 0 || n == 0) return 1;
        if (memo[m][n] > 0) return memo[m][n];
        memo[m][n] = uniquePathHelper2(m - 1, n, memo) + uniquePathHelper2(m, n - 1, memo);
        return memo[m][n];
    }

}
