package com.leetcode.algorithm.sort;

/**
 * 493. 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2  (3, 1),(3, 1)
 *
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3  (4, 1), (3, 1), (5, 1)
 *
 * 注意:
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 *
 * https://leetcode-cn.com/problems/reverse-pairs/
 */
public class LeetCode493ReversePairs {

    public static void main(String[] args) {
        //int[] nums = {1,3,2,3,1};
        int[] nums = {2,4,3,5,1,6};
        System.out.println(reversePairs(nums));
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }


    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        //int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        int cl = mergeSort(nums, left, mid);
        int cr = mergeSort(nums, mid + 1, right);
        int count = cl + cr;
        int[] cache = new int[right - left + 1];
        int i = left,  l = left, c = 0;
        //遍历 mid+1, right
        for (int j = mid + 1; j <= right; j++, c++) {
            while (i <= mid && nums[i] <= 2 * (long)nums[j])
                i++;
            while (l <= mid && nums[l] < nums[j])
                cache[c++] = nums[l++];
            cache[c] = nums[j];
            count += mid - i + 1; //总共有多少个逆序对
        }
        //遍历 left, mid
        while (l <= mid)
            cache[c++] = nums[l++];
        System.arraycopy(cache, 0, nums, left, right - left + 1);
        return count;
    }


/*    public static int reversePairs(int[] nums) {
        return reversePairsSub(nums, 0, nums.length - 1);
    }

    private static int reversePairsSub(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + ((right - left) >> 1);
        int res = reversePairsSub(nums, left, mid) + reversePairsSub(nums, mid + 1, right);
        int i = 1, j = mid + 1, k = 0, p = mid + 1;
        int[] merge = new int[right - left + 1];
        while (i <= mid) {
            while (p <= right && nums[i] > 2L * nums[p]) p++;
            res += p - (mid + 1);
            while (j <= right && nums[i] >= nums[j]) merge[k++] = nums[j++];
            merge[k++] = nums[i++];
        }
        while (j <= right) merge[k++] = nums[j++];
        System.arraycopy(merge, 0 , nums, left, merge.length);
        return res;

    }*/
}
