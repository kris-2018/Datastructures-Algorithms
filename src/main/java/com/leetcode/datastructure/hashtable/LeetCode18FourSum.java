package com.leetcode.datastructure.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * https://leetcode.com/problems/4sum/
 *
 */
public class LeetCode18FourSum {

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        for (Object o : fourSum2(nums, 0).toArray()) {
            System.out.println(o);
        }
    }

    /**
     * 回溯方法
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        getSum(nums, 0, target, res, list, 0);
        return res;
    }
    private static void getSum(int[] nums, int sum, int target, List<List<Integer>> res, List<Integer> list, int pos) {
        //1. 结束条件
        if (list.size() == 4 && sum == target && !res.contains(list)) {
            res.add(new ArrayList<>(list));
            return;
        } else if (list.size() == 4)
            return;
        for (int i = pos; i < nums.length; i++) {
            //current number with its biggest possible combination is still less than target, then we should pass to next number;
            int a = nums[i] + nums[nums.length - 1] * (3 - list.size()) + sum;
            if (a < target)
                continue;
            //current number with its smallest possible combination is still larger than target, then we should stop here.
            int b = nums[i] * (4 - list.size()) + sum;
            if (b > target)
                return;
            list.add(nums[i]);
            getSum(nums, sum + nums[i], target, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 4) return res;
        Arrays.sort(nums);
        int max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target) {
            return res;
        }
        int i, z;
        for (i = 0; i < len; i++) {
            z = nums[i];
            if (i > 0 && z == nums[i - 1])
                continue;
            if (z + 3 * max < target)
                continue;
            if (4 * z > target)
                break;
            if (4 * z == target) {
                if (i + 3 < len && nums[i + 3] == z)
                    res.add(Arrays.asList(z, z, z, z));
                break;
            }
        }
        return res;
    }

    /**
     * 时间复杂度 O(n^3)
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            //防止重复
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1]))
            for (int j = i + 1; j < nums.length - 2; j++) {
                //防止重复
                if (j == i + 1 || nums[j] != nums[j - 1]) {
                    int begin = j + 1, end = nums.length - 1, sum = target - nums[i] - nums[j];
                    while (begin < end) {
                        if (nums[begin] + nums[end] == sum) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[begin], nums[end]));
                            while (begin < end && nums[begin] == nums[begin + 1])
                                begin++;
                            while (begin < end && nums[end] == nums[end - 1])
                                end--;
                            begin++;
                            end--;
                        } else if (nums[begin] + nums[end] < sum)
                            begin++;
                        else
                            end--;
                    }
                }
            }
        }
        return res;
    }
}
