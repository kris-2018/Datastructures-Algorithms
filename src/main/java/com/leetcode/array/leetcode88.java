package com.leetcode.array;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组  Merge Sorted Array
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 *
 *  https://leetcode-cn.com/problems/merge-sorted-array/
 *
 */
public class leetcode88 {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0};
        int m = 3;
        int[] nums2 = {1,6};
        int n = 2;
        merge1(nums1, m, nums2, n);
        for (int i : nums1) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 时间复杂度为 O(m + n)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //m > 0, n > 0, k = m + n - 1 合并后新数组长度;   i, j 分别为数组num1和nums2的最后一位
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i > -1 && j > -1) nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--]; //因为两个数组都是排好序的, 都从最后一位开始比较大小, 把较大者赋值给 nums1[k--];  i-- 是先运算,后--
        while (j > -1) nums1[k--] = nums2[j--];
    }

    /**
     *  src      the source array.
     *  srcPos   starting position in the source array.
     *  dest     the destination array.
     *  destPos  starting position in the destination data.
     *  length   the number of array elements to be copied.
     * 将两个数组合并之后再排序,  时间复杂度为 (m + n)log(m + n) , 空间复杂度为 O(1) 这种方法没有利用两个数组本身已经有序这一点, 所以时间复杂度交高
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);//
        Arrays.sort(nums1);
    }

    /**
     * 可利用 插入排序 或者 归并排序的写法
     */

}
