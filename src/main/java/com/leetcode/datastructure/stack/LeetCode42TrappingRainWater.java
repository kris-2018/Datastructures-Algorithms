package com.leetcode.datastructure.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class LeetCode42TrappingRainWater {

    public static void main(String[] args) {
        int[] res = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}; //6
        int[] height = {4, 2, 0, 3, 2, 5};          //9
        System.out.println(trap2(height));
    }
    /**
     * 栈的应用
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int sum = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int curr = 0;
        while (curr < height.length) {
            //如果栈不空 且 当前指向的高度 > 栈顶高度(说明之前的积水到这里停下) 就一直循环; 否则(说明这里有积水)就入栈, curr++
            while (!stack.isEmpty() && height[curr] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.isEmpty()) break;
                int distance = curr - stack.peek() - 1; //两堵墙(两个下标)之间的距离
                int min = Math.min(height[stack.peek()], height[curr]);
                sum = sum + distance * (min - h);
            }
            stack.push(curr); //当前指向的墙入栈;
            curr++;//指针后移
        }
        return sum;
    }

    /**
     * 双指针
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
     */
    public static int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int result = 0, leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE; // leftMax represents the highest bar from left, rightMax represents the highest bar from right
        for (int left = 0, right = height.length - 1; left <= right; ) {
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

    /**
     * 栈的应用
     * @param height
     * @return
     */
    public static int trap3(int[] height) {
        if (height == null) return 0;
        Deque<Integer> stack = new LinkedList<>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] < height[stack.peek()]) {
                stack.push(i++);// stack中添加height的下标i, 然后 ++; 前值 < 后值就push进stack, 说明有积水
            } else {
                int bot = stack.pop();
                maxBotWater = stack.isEmpty() ? 0 : (Math.min(height[stack.peek()], height[i]) - height[bot]) * (i - stack.peek() - 1);
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }

}
