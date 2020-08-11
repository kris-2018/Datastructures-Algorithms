package com.leetcode.algorithm.greedy;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 *  https://leetcode-cn.com/problems/jump-game-ii/
 */
public class LeetCode45JumpGameII {
    public static void main(String[] args) {
        int[] A = {2,3,1,1,4};
        System.out.println(jump(A));
    }

    /**
     * the range of the current jump is [curBegin, curEnd]
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int count = 0, end = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                count++;
                end = farthest;
            }
        }
        return count;
    }
}
