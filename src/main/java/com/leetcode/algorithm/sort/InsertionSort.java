package com.leetcode.algorithm.sort;

/**
 * 插入排序 Insertion Sort
 * 是一种原地的稳定的排序算法;
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 1, 3, 2};
        //int[] nums = {4, 5, 6};
        int n = 6;
        insertionSort(nums, n);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }

    /**
     * 插入排序
     * 如果排序的数据是有序的，并不需搬移任何数据，从尾到头在有序数据组里面查找插入位置，每次只需要比较一个数据就能确定插入的位置。最好是时间复杂度为 O(n)。
     * 如果数组是倒序的，每次插入都相当于在数组的第一个位置插入新的数据，所以需要移动大量的数据，最坏情况时间复杂度为 O(n2)。
     * 平均时间复杂度为 O(n^2)
     * @param nums
     * @param n
     */
    public static void insertionSort(int[] nums, int n) {
        if (n <= 1) return;
        for (int i = 1; i < n; i++) {
            int value = nums[i];
            int j = i - 1;
            //查找插入位置, 如果前边的数 < value, 说明是有序的继续遍历下一个value
            for (; j >= 0; j--) {
                if (nums[j] > value) {
                    nums[j + 1] = nums[j];//数据移动交换,  第j + 1 和 j 位赋相同的值, 最后再还原为value 值
                } else
                    break;// num[j] < value, 结束循环
            }
            nums[j + 1] = value;//插入数据, 还原为value 值
        }
    }
}
