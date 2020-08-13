package com.leetcode.algorithm.divideconquer;

/**
 *
 * 如何编程求出一组数据的有序对个数或者逆序对个数呢？
 * 因为有序对个数和逆 序对个数的求解方式是类似的，所以只思考逆序对个数的求解方法。
 *
 */
public class NumberOfPairsInOrderOrReverseOrder {
    public static void main(String[] args) {
        NumberOfPairsInOrderOrReverseOrder reverseOrder = new NumberOfPairsInOrderOrReverseOrder();
        int[] array = {2, 4, 3, 1, 5, 6};
        System.out.println(reverseOrder.count(array, 6));
    }

    private int num = 0;
    public int count(int[] array, int n) {
        num = 0;
        mergeSort(array, 0, n - 1);
        return num;
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, mid, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        int i = left, j = mid + 1, k = 0;
        int[] temp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                num += (mid - i + 1); //计数逆序对的个数
                temp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        for (int p = 0; p < temp.length; p++) {
            array[left + p] = temp[p];
        }
    }
}
