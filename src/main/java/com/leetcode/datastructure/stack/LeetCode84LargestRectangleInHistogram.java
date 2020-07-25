package com.leetcode.datastructure.stack;

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
public class LeetCode84LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] res = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea3(res));
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int h = (i == heights.length - 1 ? 0 : heights[i]);
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int tp = stack.pop(); //pop: Removes the object at the top of this stack
                maxArea = Math.max(maxArea, heights[tp] * (stack.isEmpty() ? i : i - 1 - stack.peek())); //peek: Looks at the object at the top of this stack without removing it from the stack
                i--;
            }
        }
        return maxArea;
    }

    /**
     * 暴力解法 枚举左边界   右边界
     * for i -> 0, n - 2
     *   for j -> i + 1, n - 1
     *      (i, j) -->  最小高度 area
     *       update max - area
     * 时间复杂度是 O(n^3)
     *
     */
    public static int largestRectangleArea2(int[] heights) {
        int area = 0;
        //枚举左边
        for (int i = 0; i < heights.length - 1; i++) {
            //枚举右边  柱状图可以是单个,所以都是从 0, heights.length-1
            for (int j = i; j < heights.length - 1; j++) {
                //确定高度, 最小的高度
                int minHeight = Math.min(heights[i], heights[j]);
                //计算面积
                area = Math.max(area, (j - i + 1) * minHeight);
            }
        }
        return area;
    }


    /**
     * 暴力法2
     * 不用再枚举左边界就可以了，只要枚举它的棒子的高度即可。（枚举棒子高度, 找到它的左右边界即可）
     * for i -> 0, n - 1
     *    找到left bound     right bound
     *    area = height[i] * (right - left)
     *    update max - area
     * {2,1,5,6,2,3}
     *
     */
    public static int largestRectangleArea3(int[] heights) {
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            int width = 1, j = i; //宽度至少为1
            //往左边找
            while (--j >= 0 && heights[j] >= heights[i]) {
                width++;
            }
            j = i;
            //往右边找
            while (++j < heights.length && heights[j] >= heights[i]) {
                width++;
            }
            //记录最大面积
            area = Math.max(area, width * heights[i]);
        }
        return area;
    }

}
