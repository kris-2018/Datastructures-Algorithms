package com.leetcode.algorithm.recursive.divideconquer;

import java.util.Arrays;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 [ n/2 ] 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * https://leetcode-cn.com/problems/majority-element/description/
 *
 */
public class LeetCode169MajorityElement {
    public static void main(String[] args) {
        int[] num = {1,2,3,3,4,4};
        System.out.println(majorityElement(num));
        //System.out.println(majorityElement2(num));
    }



    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
    /**
     *
     * O(n) time O(1) space
     *
     * @param num
     * @return
     */
    public static int majorityElement2(int[] num) {
        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                count++;
                major=num[i];
            } else if (major == num[i]) {
                count++;
            } else count--;
        }
        return major;
    }
}
