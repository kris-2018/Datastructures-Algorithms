package com.leetcode.algorithm.sort;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = {11, 8, 3, 9, 7, 1, 2, 5};
        mergeSort(nums, 0, 7);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        //1. 递归终止条件
        if (left >= right) return;
        int mid = (left + right) >> 1;
        //2. 分治递归
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        //3. 将array[left, mid]排序 和 array[mid + 1, right]排序 并合并为 array[left, right]
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        //临时缓存 数组
        int[] temp = new int[right - left + 1];
        //mid中间下标, i -> mid 往左遍历, mid -> right 往右遍历； i, j分别指向array[left, mid] 和 array[mid+1, right] 的第一个元素
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        //将剩余的数据拷贝到临时数组 temp
        while (i <= mid)
            temp[k++] = array[i++];
        //将剩余的数据拷贝到临时数组 temp
        while (j <= right)
            temp[k++] = array[j++];
        //把临时数组temp中数组拷贝到原数组中。
        for (int p = 0; p < temp.length; p++) {
            array[left + p] = temp[p];
        }
        //System.arraycopy(temp, 0, array, left, right - left + 1); // 也可以⽤ System.arraycopy(a, start1, b, start2, length)
    }
}
