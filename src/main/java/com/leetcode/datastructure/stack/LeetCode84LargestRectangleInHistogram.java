package com.leetcode.datastructure.stack;

import java.util.Deque;
import java.util.LinkedList;
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
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/javade-5chong-jie-fa-xiao-lu-zui-gao-de-ji-bai-lia/
 * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28900/Short-and-Clean-O(n)-stack-based-JAVA-solution
 */
public class LeetCode84LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] res = {2,1,5,6,2,3};
        int[] res2 = {1}; // 1
        System.out.println(largestRectangleArea(res2));
    }

    /**
     * 栈
     * 以当前柱子的高度为矩形的高, 只需要往左和往右找到小于当前的柱子，就可以确定矩形的宽度
     * 矩形的宽度怎么求, 要维护一个递增的栈（从栈底到栈顶的元素所对应柱子的高度是递增的）
     *   如果当前柱子i的值大于等于栈顶元素对应柱子的高度，我们就把当前柱子的下标压入到栈顶中。
     *   如果当前柱子i的值小于栈顶元素柱子k的高度，说明栈顶元素对应的柱子k遇到了右边比它小的柱子，我们只需要弹出栈顶柱子k。
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) { // 考虑到边界问题(比如只为1个长方形) 这里要为 <=, 而不是 <
            int h = (i == heights.length ? 0 : heights[i]);
            //如果栈是空的，或者当前柱子的高度大于等于栈顶元素所对应柱子的高度, 直接把当前元素即heights的下标 入栈
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int tp = stack.pop(); //pop: Removes the object at the top of this stack
                //peek: Looks at the object at the top of this stack without removing it from the stack
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea, heights[tp] * width);
                i--;
            }
        }
        return maxArea;
    }

    /**
     * 暴力解法 枚举左边界   右边界
     * for i -> 0, n - 1
     *   for j -> i, n - 1
     *      (i, j) -->  最小高度 area
     *       update max - area
     * 时间复杂度是 O(n^2)
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
