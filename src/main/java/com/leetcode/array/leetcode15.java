package com.leetcode.array;

import java.util.*;

/**
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * 不包含重复元素，可使用Set，它的实现类有HashSet,
 *                                  TreeSet,
 *                                 LinkedHashSet(是HashSet的子类，但是比HashSet要多一个“记录元素添加顺序的功能”，因此它添加和删除的效率比HashSet相对低。)
 *
 */

public class leetcode15 {
    public static void main(String[] args) {
        int arr[] = {-1,0,1,2,-1,-4};
        leetcode15 leetcode15 = new leetcode15();

        for (List<Integer> integers : leetcode15.threeSum2(arr)) {
            System.out.println(integers);
        }
    }


    /**
     * 方法二 双指针解法
     * @param num
     * @return
     */
    public List<List<Integer>> threeSum2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }


    /**
     * 方法一 这种暴力解法会超出时间限制
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //int[] res;
        Arrays.sort(nums); //一定要先排好序，不然结果是错误的
        Set<List<Integer>> result = new LinkedHashSet<>();
        for (int i = 0;i < nums.length - 2;i++){
            for (int j = i + 1;j < nums.length - 1;j++){
                for (int k = j + 1;k < nums.length;k++){
                    if (nums[i] + nums[j] + nums[k] == 0){
                        //int[] res = {};
                        List<Integer> value = Arrays.asList(nums[i], nums[j], nums[k]);
                        result.add(value);
                    }
                }
            }
        }
        return new ArrayList<List<Integer>>(result);
    }


}
