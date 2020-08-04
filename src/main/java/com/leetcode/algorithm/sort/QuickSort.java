package com.leetcode.algorithm.sort;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        //int[] array = {8, 10, 2, 3, 6, 1, 5};
        int[] nums = {11, 8, 3, 9, 7, 1, 2, 5};
        quickSort(nums, 0, 7);
        for (int arr : nums) {
            System.out.print(arr + "\t");
        }
    }

    public static void quickSort(int[] array, int begin, int end) {
        //1. 递归结束条件
        if (begin >= end) return;
        int pivot = partition(array, begin, end); //返回 counter 个数 (中间值左边数组 或者 右边数组的个数)
        quickSort(array, begin, pivot - 1); //递归左边的值
        quickSort(array, pivot + 1, end); //递归右边的值
    }
    private static int partition(int[] array, int begin, int end) {
        // pivot: 标杆位置,  counter: ⼩于pivot的元素的个数, 从左往右即从小到大计数
        int pivot = end, counter = begin;
        //1. 把小于 array[pivot] 的元素依次放数组左边,
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        //2. 将 array[pivot] 的值中间, 左边 < array[pivot] < 右边
        int temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;
        return counter;
    }
}
