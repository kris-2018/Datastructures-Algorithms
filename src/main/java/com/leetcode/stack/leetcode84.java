package com.leetcode.stack;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形  Largest Rectangle in Histogram
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 比如 每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 *
 */
public class leetcode84 {

    public static void main(String[] args) {
        int[] res = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(res));
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int h = (i == heights.length - 1 ? 0 : heights[i]);
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int tp = stack.pop();
                maxArea = Math.max(maxArea, heights[tp] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
                i--;
            }
        }
        return maxArea;
    }
}
