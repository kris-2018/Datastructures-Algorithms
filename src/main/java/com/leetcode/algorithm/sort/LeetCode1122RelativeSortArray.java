package com.leetcode.algorithm.sort;

import java.util.TreeMap;

/**
 * 1122. 数组的相对排序
 * 给你两个数组，arr1 和 arr2，
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *
 *
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 *
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * https://leetcode-cn.com/problems/relative-sort-array/
 */
public class LeetCode1122RelativeSortArray {

    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};

        for (int i : relativeSortArray(arr1, arr2)) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 计数排序 counting sort
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] cnt = new int[1001];
        //遍历 arr1 将其元素作为下标统计元素个数插入到cnt中;
        for(int n : arr1)
            cnt[n]++;
        int i = 0;
        //遍历 arr2 将其插入到 arr1中
        for (int n : arr2) {
            while (cnt[n]-- > 0) {
                arr1[i++] = n;
            }
        }
        //将arr1 中剩余的元素插入
        for (int n = 0; n < cnt.length; n++) {
            while (cnt[n]-- > 0) {
                arr1[i++] = n;
            }
        }
        return arr1;
    }

    /**
     * TreeMap
     *  O(nlogn)
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n : arr1) map.put(n, map.getOrDefault(n, 0) + 1);
        int i = 0;
        for (int n : arr2) {
            for (int j = 0; j < map.get(n); j++) {
                arr1[i++] = n;
            }
            map.remove(n);
        }
        for (int n : map.keySet()) {
            for (int j = 0; j < map.get(n); j++) {
                arr1[i++] = n;
            }
        }
        return arr1;
    }
}
