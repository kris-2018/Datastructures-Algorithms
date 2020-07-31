package com.leetcode.algorithm.sort;

/**
 * 选择排序（Selection Sort）
 *
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 1, 3, 2};
        //int[] nums = {4, 5, 6};
        int n = 6;
        selectionSort(nums, n);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }

    /**
     * 选择排序（Selection Sort）
     * @param nums
     * @param n
     */
    public static void selectionSort(int[] nums, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n - 1; i++) {
            //查找最小值 的下标 index
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            //交换
            int tmp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = tmp;
        }
    }
}
