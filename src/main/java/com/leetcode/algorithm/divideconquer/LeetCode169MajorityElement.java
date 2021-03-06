package com.leetcode.algorithm.divideconquer;

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
 *
 *
 *
 *  Majority 最后一定会有
 * [1,3,3,2,3]
 * [1,1,1,0,2]
 * count(x) > n/2
 *
 * 题目:求众数
 * 示例一:
 * 输入: [1,3,3,2,3]
 * 输出: 3
 * 示例二:
 * 输入: [1,1,1,0,2]
 * 输出: 1
 *
 * ①暴力做法: 两个循环
 * 枚举x, loop: x
 *          loop count(x) --针对每个x去数组里边去计数, 把count最大的x返回
 * 时间复杂度是O(N^2)
 *
 * ②Map {x: count_x}
 *     Loop ==> Map count
 * 时间复杂度是O(N), 空间复杂度也是O(N)
 *
 * ③sort[1,2,3,3,3]
 * 只要重复的元素 > n/2
 * 时间复杂度是O(NlogN)
 *
 * ④分治 Divide & Conquer
 * 一分为二, 左右两边各找一个majority, 如果相等即可
 * 否则就看左边和右边哪边的count大, 如果左边大就返回左边，否则返回右边
 * left == right ==> left
 * else return count(l) > count(r)
 * 时间复杂度是O(Nlog(N))
 * 针对[1,2,3]这种数组可能无法解决, 可能返回1,2,3
 *
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
