package com.leetcode.algorithm.binarysearch;

/**
 * 33. 搜索旋转排序数组  Search in Rotated Sorted Array
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 *  https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 */
public class LeetCode33SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));
    }

 /*   public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            } else {
                return mid;
            }
        }
        return -1;
    }
*/
    /**
     *  binary search 0ms
     *  时间复杂度 log(n), n为数组长度
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                //中间值 > 目标值 --> 如左边值 < 中间值 &&
                //                   左边值 > 目标值
                //              ---> 说明中间值往左是有序的,就往右边查找, 否则往左
                if (nums[left] <= nums[mid] && nums[left] > target) {
                    left = mid + 1; //   目标值 < 左边值, 往右边找
                } else {
                    right = mid - 1; //  目标值 > 左边值, 往左边找
                }
            } else if (nums[mid] < target) {
                if (nums[right] >= nums[mid] && nums[right] < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { //nums[mid] == target
                return mid;
            }
        }
        return -1;
    }
}
