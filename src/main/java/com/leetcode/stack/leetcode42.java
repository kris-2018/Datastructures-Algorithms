package com.leetcode.stack;

import java.util.Stack;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class leetcode42 {

    public static void main(String[] args) {
        int[] res = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(res));
    }

    public static int trap(int[] height) {
        if (height == null) return 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] < height[stack.peek()]) {
                stack.push(i++);
            } else {
                int bot = stack.pop();
                maxBotWater = stack.isEmpty() ? 0 : (Math.min(height[stack.peek()], height[i]) - height[bot]) * (i - stack.peek() - 1);
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }

    /**
     * 双指针
     * https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
     */
    public static int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int result = 0, leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
        for (int left = 0, right = height.length - 1; left <= right;) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            //how much can current position trap depends on the shorter bar (木桶原理)
            if (leftMax < rightMax) {
                result += leftMax - height[left];
                left++;
            } else {
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }
}
