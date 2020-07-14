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
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class LeetCode47PermutationsII {
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        for (Object o : permuteUnique(nums).toArray()) {
            System.out.println(o);
        }
    }


    public static List<List<Integer>> permuteUnique(int[] nums) {
        //准备工作, result存放结果, list临时存放,  used标记nums中元素是否被使用
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; //indicate whether the value is added to list
        //为num为null或0的情况
        if (nums == null || nums.length == 0) return res;
        //排序是剪枝的前提
        Arrays.sort(nums); // make sure we can skip the same value.
        dfs(nums, used, list, res);
        return res;
    }

    private static void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        //1. if 满足结束条件
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        //2. 遍历迭代选择列表
        for (int i = 0; i < nums.length; i++) {
            //2.1 做选择
            if (used[i]) {
                continue;//结束本层的本次循环
            }
            //剪枝条件: i > 0 是为了保证 nums[i - 1] 有意义
                   // !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i >0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                continue;
            }
            used[i] = true; //标记used[i]已被使用.
            list.add(nums[i]);
            dfs(nums, used, list, res);
            //2.2 撤销选择
            // 回溯部分的代码，和 dfs 之前的代码是对称的
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
