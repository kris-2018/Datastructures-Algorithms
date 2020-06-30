package com.leetcode.algorithm.recursive.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 46. 全排列  Permutations
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 *  https://leetcode-cn.com/problems/permutations/
 *
 *  回溯法: 一种通过探索所有可能的候选解来找出所有的解的算法。如果候选解被确认不是一个解的话（或者至少不是最后一个解），回溯算法会通过在上一步进行一些变化抛弃该解，即回溯并且再次尝试。
 *
 */
public class leetcode46 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        for (Object o : permute(nums).toArray()) {
            System.out.println(o);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<Integer>(), nums);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue; //tempList中包含就返回继续循环迭代
                tempList.add(nums[i]); // 遍历整个数组nums, 添加到tempList(之前要先判断是否包含nums[i] ), 防止重复
                backtrack(list, tempList, nums); //每次递归i 从0开始;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
