package com.leetcode.datastructure.array;

import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 */
public class LeetCode01TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 6, 7};
        LeetCode01TwoSum leetcode01 = new LeetCode01TwoSum();
        for (int i: leetcode01.twoSum3(nums,9)){
            System.out.print(i + "\t");
        }
    }

    /**
     * 方法一: 暴力解法  2层for循环, 时间复杂度为 O(n^2)  空间复杂度为 O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target){
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    return new int[] {i,j};
                }
            }
        }
        throw new IllegalArgumentException("No tow sum sollution");
    }

    /**
     * 方法二: 利用哈希表
     * 数组下标对应的值为 K, 下标为 V , 将每个键值对存入map中, 遍历
     * 时间复杂度为 O(n)  空间复杂度为 O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        //int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { map.get(target - nums[i]), i };
                //result[1] = i;
                //result[0] = map.get(target - nums[i]);
                //return result;
            }
            map.put(nums[i], i); // 下标对应的值为 K, 下标为 V
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法三: 排序 + 双指针
     * 1. copy an array, and sort it using quick sort, O(nlogn)
     * 2. 双指针遍历, 使用数组indices记录两个数值;
     * 3. 再遍历数组由数值找下标index O(n)
     * 根据时间复杂度的加法法则, 总的时间复杂度为 O(nlogn)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        if (nums == null) return null;
        /* 1. copy, sort */
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums2);
        /* 2. tow points */
        int start = 0, end = nums2.length - 1;
        int[] indices = new int[2];
        while (start < end) {
            int sum = nums2[start] + nums2[end];
            if (sum == target) {
                indices[0] = start;
                indices[1] = end;
            } else if (sum < target) start++;
            else  end--;
        }
        /* 3. 遍历数组由数值找下标index */
        for (int i = 0; i < nums.length; i++) {
            if (nums2[indices[0]] == nums[i]) {
                indices[0] = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums2[indices[1]] == nums[i]) {
                indices[1] = i;
                break;
            }
        }
        return indices;
    }
}
