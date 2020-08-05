package com.leetcode.algorithm.sort;

/**
 * 计数排序
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 0, 2, 3, 0, 3};
        countingSort(arr, 8);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    public static void countingSort(int[] arr, int n) {
        if (n <= 1) return;
        //查找数组中数据范围
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //申请一个计数数组count表示桶的大小, 下标大小[0, max] 下标对应arr数组元素的值
        int[] count = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            count[i] = 0;
        }
        //计算每个元素个数, 放入count中
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }
        //依次累加, count数组进行顺序累加求和
        for (int i = 1; i <= max; i++) {
            count[i] = count[i - 1] + count[i];
        }
        //临时数组temp, 存储排序之后的结果
        int[] temp = new int[n];
        //计数排序的关键步骤
        for (int i = n - 1; i >= 0; i--) {
            int index = count[arr[i]] - 1;
            temp[index] = arr[i];
            count[arr[i]]--;//将count中相对应个数 -1
        }
        //将结果拷贝给arr数组
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }
}
