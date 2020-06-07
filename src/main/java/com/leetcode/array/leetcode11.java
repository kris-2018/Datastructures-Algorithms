package com.leetcode.array;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */

public class leetcode11 {

    public static void main(String[] args) {
        leetcode11 leetcode11 = new leetcode11();
        int[] nums = {0,2,5,7,8,9};
        System.out.println(leetcode11.maxArea2(nums));
    }


    /**
     * 单层for循环, 双指针解法, 时间复杂度O(n); 为什么双指针往中间移动时，不会漏掉某些情况
     * @param a
     * @return
     */
    public int maxArea2(int[] a){
        int max = 0;
        for (int i = 0,j = a.length - 1;i < j;){
            int minHeight = a[i] < a[j] ? a[i++] : a[j--]; //谁的高度小谁移动
            int area = (j - i + 1) * minHeight;
            max = Math.max(max, area);
        }
        return max;
    }

    /**
     * 2层for循环 时间复杂度O(n^2)
     * @param a
     * @return
     */
    public int maxArea(int[] a){
            int max = 0;
            for (int i = 0;i < a.length - 1; i++){
                for (int j = i + 1; j < a.length; j++){
                    int area = (j - i) * Math.min(a[i], a[j]);
                    max = Math.max(max, area);
                }
            }
            return max;
    }

}
