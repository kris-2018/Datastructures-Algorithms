package com.leetcode.datastructure.heap;

/**
 *
 * i = 1 存储根节点，下标为 i 的节点的左子节点就是下标为 i ∗ 2 的节点，
 *       右子节点就是下标为 i ∗ 2 + 1 的节点，
 *       父节点就是下标为 i / 2 的节点；
 *
 */
public class Heap {
    public static void main(String[] args) {
        Heap heap = new Heap(7);
        heap.insert(7);
        heap.insert(5);
        heap.insert(6);
        heap.insert(4);
        heap.insert(2);
        heap.insert(1);
        heap.insert(8);

        for (int arr : heap.array) {
            System.out.print(arr + "\t");
        }
        heap.removeMax();
        System.out.println("\n移除堆顶元素");
        for (int arr : heap.array) {
            System.out.print(arr + "\t");
        }
        int[] array = {0, 7, 5, 6, 4, 2, 1};
        heap.buildHeap(array, 6);

        System.out.println("\n堆排序");
        heap.sort(array, 6);
        for (int arr : array) {
            System.out.print(arr + "\t");
        }


    }

    private int[] array; // 数组，从下标 1 开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        array = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    /**
     * 插入操作 自下往上堆化
     * @param data
     */
    public void insert(int data) {
        if (count >= n) return; // 堆满了
        count++;
        array[count] = data;
        int i = count;
        //i当前节点下标,  i/2父节点下标, 堆中每一个节点的值都必须小于等于其子树中每个节点的值(大顶堆)。
        while (i/2 > 0 && array[i] > array[i/2]) { // 自下往上堆化
            swap(array, i, i/2); // swap() 函数作用: 交换下标为 i 和 i/2 的两个元素
            i = i/2;
        }
    }
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 删除操作 自上往下堆化
     */
    public void removeMax() {
        if (count == 0) return; // 堆中没有数据
        array[1] = array[count];
        count--;
        heapify(array, count, 1); // 自上往下堆化
    }
    private void heapify(int[] array, int n, int i) {
        while (true) {
            int maxPos = i;
            //i当前节点下标, i*2 为其左子节点下标
            if (i*2 <= n && array[i] < array[i*2])
                maxPos = i*2;
            //i当前节点下标, i*2+1 为其右子节点下标
            if (i*2+1 <= n && array[maxPos] < array[i*2+1])
                maxPos = i*2+1;
            if (maxPos == i)
                break;
            swap(array, i, maxPos);
            i = maxPos;
        }
    }


    /**
     * 构建一个堆
     * 叶子节点不需要堆化, 下标是 [n/2 + 1, n]的节点是叶子节点 ;
     *                  下标是 [n/2, 1]的数据进行堆化.
     * @param array 数组
     * @param n 下标
     */
    private void buildHeap(int[] array, int n) {
        for (int i = n/2; i >= 1; --i) {
            heapify(array, n, i);
        }
    }

    /**
     * 基于堆实现排序:
     *  1. 构建一个堆(大顶堆);
     *  2. 堆顶元素即最大的元素, 把它跟最后一个元素交换, 那最大元素就放到了下标为 n 的位置。
     *  3. 然后再通过堆化的方法, 将剩下的 n−1 个元素重新构建成堆。
     *  4. 重复这个过程, 直到堆中元素只剩下下标为1 的一个元素, 排序完成。
     * n 表示数据的个数, 数组 array 中的数据从下标 1 到 n 的位置。
     * 先堆化
     * @param array 数组
     * @param n 下标
     */
    public void sort(int[] array, int n) {
        buildHeap(array, n);
        int k = n;
        while (k > 1) {
            swap(array, 1, k);
            k--;
            heapify(array, k, 1);
        }
    }

}
