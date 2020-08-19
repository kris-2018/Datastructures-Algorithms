package com.leetcode.algorithm.dynamicprogram;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 *  进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class LeetCode53MaxSubarray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    /**
     * 动态规划
     * 时间复杂度O(n)
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        //i从1开始
        for (int i = 1; i < nums.length; i++) {
            //dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            dp[i] = nums[i] + Math.max(0, dp[i-1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 分治
     * 时间复杂度 O(nlogn)
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }
    private static int helper(int[] nums, int left, int right) {
        if (left > right) return Integer.MIN_VALUE;
        if (left == right) return nums[left];
        int mid = (left + right) / 2;
        int l = helper(nums, left, mid - 1);
        int r = helper(nums, mid + 1, right);
        int leftSum = 0;
        int temp = 0;
        for (int i = mid - 1; i >= left; i--) {
            temp += nums[i];
            if (temp > leftSum) leftSum = temp;
        }
        temp = 0;
        int rightSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            temp += nums[i];
            if (temp > rightSum) rightSum = temp;
        }
        return Math.max(Math.max(l, r), leftSum + rightSum + nums[mid]);

    }
}
