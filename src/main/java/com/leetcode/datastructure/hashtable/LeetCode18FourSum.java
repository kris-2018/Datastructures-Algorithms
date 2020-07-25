package com.leetcode.datastructure.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * https://leetcode.com/problems/4sum/
 *
 */
public class LeetCode18FourSum {


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        getSum(nums, 0, target, res, list, 0);
        return res;
    }

    private void getSum(int[] nums, int sum, int target, List<List<Integer>> res, List<Integer> list, int pos) {
        if (list.size() == 4 && sum == target && !res.contains(list)) {
            res.add(new ArrayList<>(list));
            return;
        } else if (list.size() == 4) return;
        for (int i = pos; i < nums.length; i++) {
            if (nums[i] + nums[nums.length - 1] * (3 - list.size()) + sum < target) continue;
            if (nums[i] * (4 - list.size()) + sum > target) return;
            list.add(nums[i]);
            getSum(nums, sum + nums[i], target, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> fourSum1(int[] nums, int target) {
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
}
