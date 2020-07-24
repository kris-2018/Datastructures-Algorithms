package com.leetcode.algorithm.binarysearch;

public class binarysearch {

    public static void main(String[] args) {
        final binarysearch binarysearch = new binarysearch();
        int[] nums = {8,11,19,23,27,33,45,55,67,98};
        System.out.println(binarysearch.bsearch(nums, 10, 19));


        //变体一: 查找第一个值等于给定值的元素 a[5] == 8;
        int[] nums3 = {1,3,4,5,6,8,8,8,11,18};
        System.out.println(binarysearch.bsearch3(nums3, 10, 8));

        //变体二：查找最后一个值等于给定值的元素 a[7] == 8;
        System.out.println(binarysearch.bsearch4(nums3, 10, 8));
    }

    /**
     * 1.循环退出条件是 low <= high，而不是 low < high。
     * 2.mid = (low + high) / 2这种写法，在 low 和 high 比较大时，两者之和可能会溢出。
     *    改进的方法是将 mid 的计算方式写成 low + (high - low) / 2， 转化成位运算 low+((high-low)>>1) 更佳。
     * 3.low=mid+1，high=mid-1。注意这里的 +1 和 -1，如果直接写成 low=mid 或者 high=mid，就可能会发生死循环。
     *    比如，当 high=3，low=3 时，如果 a[3] 不等于 value，就会导致一直循环不退出。
     * 时间复杂度为 O(logn)
     * @param arr
     * @param n
     * @param value
     * @return
     */
    public int bsearch(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;   // (low + high) / 2, 可能会发生溢出
            if (arr[mid] > value ) {
                high = mid - 1;
            } else if (arr[mid] < value) { //
                low = mid + 1;
            } else { // arr[mid] == value
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找的递归实现
     * @param arr
     * @param n
     * @param val
     * @return
     */
    public int bsearch2(int[] arr, int n, int val) {
        return bsearchInternally(arr, 0, n - 1, val);
    }
    private int bsearchInternally(int[] arr, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (arr[mid] > value) {
            return bsearchInternally(arr, low, mid - 1, value);
        } else if (arr[mid] < value) {
            return bsearchInternally(arr, mid + 1, high, value);
        } else {
            return mid;
        }
    }


    /**
     * 变体一: 查找第一个值等于给定值的元素
     *
     * @param arr
     * @param n
     * @param value
     * @return
     */
    public int bsearch3(int arr[], int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (arr[mid - 1] != value)) return mid;
                else high = mid -1;
            }
        }
        return -1;
    }

    public int bsearch4(int arr[], int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (arr[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
}
