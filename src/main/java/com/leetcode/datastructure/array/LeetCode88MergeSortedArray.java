package com.leetcode.datastructure.array;

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
public class LeetCode88MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {6,7,8,0,0,0};
        int m = 3;
        int[] nums2 = {4,5,6};
        int n = 3;
        merge(nums1, m, nums2, n);
        for (int i : nums1) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 双指针倒序比较插入
     * 时间复杂度为 O(m + n)
     * 空间复杂度 O(1)
     * insert from the m+n-1 position at the bigger array
     * keep 3 pointers:
     *                one at the insertion point
     *                one at the end of nums1;
     *                one at the end of nums2
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //m > 0, n > 0, k = m + n - 1 合并后新数组长度;   i, j 分别为数组num1和nums2的最后一位
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0)
            //因为两个数组都是排好序的, 都从最后一位开始比较大小, 把较大者赋值给 nums1[k--] 最后一个位置 ;  i-- 是先运算,后--
            nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        while (j >= 0) //剩下nums2,倒序插入即可; 如果剩下nums1, 直接就是其中的值
            nums1[k--] = nums2[j--];
        //System.arraycopy(nums2,0,nums1,0,j+1); //最后一个while循环可改成此处; nums1和nums2比较完之后, 剩下的即是最小的了,arraycopy完成即可,j为nums2的下标,此处参数为nums2的length, 所以+1即可;
    }

    /**
     *  src      the source array.
     *  srcPos   starting position in the source array.
     *  dest     the destination array.
     *  destPos  starting position in the destination data.
     *  length   the number of array elements to be copied.
     * 将两个数组合并之后再排序,  时间复杂度为 (m + n)log(m + n) , 空间复杂度为 O(1) 这种方法没有利用两个数组本身已经有序这一点, 所以时间复杂度较高
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);//
        Arrays.sort(nums1);
    }

    /**
     * 可利用 插入排序 或者 归并排序的写法
     */
    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        //正序遍历nums2, 把nums2的第i个元素插入nums1的末尾
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
            //倒序遍历nums1, 进行插入排序
            for (int j = m + i; j > 0; j--) {
                if (nums1[j - 1] > nums1[j]) {
                    int temp = nums1[j - 1];
                    nums1[j - 1] = nums1[j];
                    nums1[j] = temp;
                } else
                    break;
            }
        }
    }


}
