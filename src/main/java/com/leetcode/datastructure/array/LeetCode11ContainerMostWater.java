package com.leetcode.datastructure.array;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * <p>
 * https://leetcode-cn.com/problems/container-with-most-water/
 */

public class LeetCode11ContainerMostWater {

    public static void main(String[] args) {
        LeetCode11ContainerMostWater leetcode11 = new LeetCode11ContainerMostWater();
        int[] nums = {1, 2, 8, 9, 5, 7};
        System.out.println(leetcode11.maxArea2(nums));
    }

    /**
     * 双指针解法 时间复杂度O(n)
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return maxArea;
    }

    /**
     * 单层for循环, 双指针解法, 时间复杂度O(n);
     * 为什么双指针往中间移动时，不会漏掉某些情况;  缩减搜索空间
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            int minHeight = height[i] < height[j] ? height[i++] : height[j--]; //谁的高度小谁移动
            int area = (j - i + 1) * minHeight; //(j - i + 1) 因为上边进行了 i++ 或者 j--操作
            max = Math.max(max, area);
        }
        return max;
    }

    /**
     * 2层for循环 时间复杂度O(n^2)
     * @param height
     * @return
     */
    public int maxArea3(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(max, area);
            }
        }
        return max;
    }

}
