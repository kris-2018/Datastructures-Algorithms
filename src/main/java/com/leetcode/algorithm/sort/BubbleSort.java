package com.leetcode.algorithm.sort;

/**
 * 冒泡排序
 * 是一种原地的稳定的排序算法;
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 3, 2, 1};
        //int[] nums = {4, 5, 6};
        int n = 6;
        bubbleSort(nums, n);
        for (int num : nums) {
            System.out.print(num + "\t");
        }

    }

    /**
     * 按从小到大排序, 大的依次往后移
     * 如果原数据已经是有序的, 最好情况时间复杂度为 O(n)
     * 如果原数据刚好是倒序的, 最坏情况时间复杂度为 O(n^2)
     *                           平均时间复杂度O(n^2)
     * @param nums
     * @param n
     */
    public static void bubbleSort(int[] nums, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - 1; j++) {
                if (nums[j] > nums[j+1]) { // 交换
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    flag = true; // 表示有数据交换
                }
            }
            if (!flag) //没有数据交换, 说明是有序的, 提前退出
                break;
        }
    }
}
