package com.leetcode.algorithm.sort;

/**
 * 堆排序
 */
public class HeapifySort {

    public static void main(String[] args) {
        int[] array = {7, 5, 19, 8, 4, 1, 20, 13, 16};
        heapSort(array);
        for (int arr : array) {
            System.out.print(arr + "\t");
        }
    }

    /**
     * 2. 排序
     * @param array
     */
    public static void heapSort(int[] array) {
        if (array.length == 0) return;
        int length = array.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(array, length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

    /**
     * 1. 建堆
     * @param array
     * @param length
     * @param i
     */
    public static void heapify(int[] array, int length, int i) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int largest = i;
        if (left < length && array[left] > array[largest]) {
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, length, largest);
        }
    }
}
