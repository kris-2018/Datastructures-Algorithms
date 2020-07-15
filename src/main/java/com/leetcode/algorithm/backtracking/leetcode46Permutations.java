package com.leetcode.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 46. 全排列  Permutations
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 排列组合的数学题, n 个不重复的数, 全排列共有 n! 个。
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
 * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
 *
 */
public class leetcode46Permutations {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        for (Object o : permute(nums).toArray()) {
            System.out.println(o);
        }
    }

    /**
     *回溯
     * 在递归之前做出选择，在递归之后撤销刚才的选择，就能正确得到每个节点的选择列表和路径。
     * 3ms
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<Integer>(), nums);
        return list;
    }
    /** 路径记录: tempList,
     *  选择列表: nums中不存在tempList中的那些元素
     *  结束条件: nums中的数组全都在tempList中出现。
     *  */
    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        // 1. 触发结束条件(满足结束条件)
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        // 2.
        for (int i = 0; i < nums.length; i++) {
            //做选择
                //排除不合法的选择
            if (tempList.contains(nums[i]))
                continue; //tempList中包含就返回继续循环迭代
            tempList.add(nums[i]); // 遍历整个数组nums, 添加到tempList(之前要先判断是否包含nums[i] ), 防止重复
            backtrack(list, tempList, nums); //每次递归i 从0开始;
            //撤销选择
            tempList.remove(tempList.size() - 1);
        }

    }
}
