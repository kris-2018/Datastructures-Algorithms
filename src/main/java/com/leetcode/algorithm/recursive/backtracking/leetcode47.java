package com.leetcode.algorithm.recursive.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 *
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * https://leetcode.com/problems/permutations-ii/
 */
public class leetcode47 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        for (Object o : permuteUnique(nums).toArray()) {
            System.out.println(o);
        }
    }


    public static List<List<Integer>> permuteUnique(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length]; //indicate whether the value is added to list
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums); // make sure we can skip the same value.
        dfs(nums, used, list, res);
        return res;
    }

    private static void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i >0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
            used[i] = true;
            list.add(nums[i]);
            dfs(nums, used, list, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
