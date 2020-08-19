package com.leetcode.algorithm.dynamicprogram;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * https://leetcode-cn.com/problems/coin-change/
 */
public class LeetCode322CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange3(coins, amount));
    }

    /**
     * 动态规划: 自下而上
     * dp[i] 表示 组成金额 i 所需最少的硬币数量的最小值
     * 时间复杂度 O(Sn) S是金额, n是面额数量
     * 空间复杂度 O(S)
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (coins[i] <= j) // coins[i] <= j  ----   j总金额数, i是要凑的面值数, coins[i] <= j即coins里的面值可以用
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * BFS
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange2(int[] coins, int amount) {
        if (amount == 0) return 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount+1];
        visited[amount] = true;
        queue.offer(amount);
        //排序是为了加快广度优先遍历过程中，对***面值的遍历，起到剪枝的效果
        Arrays.sort(coins);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer head = queue.poll();
                for (int coin: coins) {
                    int next = head - coin;
                    //只要遇到0, 就找到了一个最短路径
                    if (next == 0) return step;
                    if (next < 0) break;
                    if (!visited[next])
                        queue.offer(next);
                        visited[next] = true;
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * 递归
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange3(int[] coins, int amount) {
        if (amount == 0) return 0;
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount < coin) continue;
            int ret = coinChange3(coins, amount - coin);
            if (ret == -1) continue;
            result = Math.min(result, ret + 1);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * 记忆化搜索
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange4(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        Integer[] memo = new Integer[amount + 1];
        return dfsHelper(coins, amount, memo);
    }
    private static int dfsHelper(int[] coins, int amount, Integer[] memo) {
        if (amount == 0) return 0;
        if (memo[amount] != null) return memo[amount];
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount < coin) continue;
            int ret = dfsHelper(coins, amount - coin, memo);
            if (ret == -1) continue;
            result = Math.min(result, ret + 1);
        }
        result = result == Integer.MAX_VALUE ? -1 : result;
        memo[amount] = result;
        return memo[amount];
    }

}
