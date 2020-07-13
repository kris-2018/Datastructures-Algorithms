package com.leetcode.algorithm.recursive.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * https://leetcode-cn.com/problems/combinations/
 *
 * 参考
 * https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
 *
 */
public class LeetCode77Combinations {

    public static void main(String[] args) {
        for (Object o : combine2(4, 2).toArray()) {
            System.out.println(o);
        }
    }

    /**
     * 回溯算法
     * 画图,展现递归结构
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        combine(lists, new ArrayList<Integer>(), 1, n, k);
        return lists;
    }
    private static void combine(List<List<Integer>> lists, List<Integer> tempList, int start, int n, int k) {
        // 如果为 0 ,直接返回 [] 空值, 递归结束条件
        if (k == 0) {
            lists.add(new ArrayList<Integer>(tempList));
            return;
        }
        for (int i = start; i <= n; i++) {
            tempList.add(i);
            combine(lists, tempList, i + 1, n, k - 1);
            tempList.remove(tempList.size() - 1); //移除index最后一位的元素
        }
    }

    /**
     * 回溯 + 剪枝
     * 剪枝过程就是：把 i <= n 改成 i <= n - (k - pre.size()) + 1
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        backtranck(lists, new ArrayList<>(), 1, n, k);
        return lists;
    }

    private static void backtranck(List<List<Integer>> lists, List<Integer> tempList, int start, int n, int k) {
        if (k == 0) {
            lists.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            tempList.add(i);
            backtranck(lists, tempList, i + 1, n, k - 1);
            tempList.remove(tempList.size() - 1);
        }
    }

}
