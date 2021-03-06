package com.leetcode.datastructure.array;

import java.util.*;

/**
 * 15. 三数之和
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

public class LeetCode15Sum3 {
    public static void main(String[] args) {
        int arr[] = {};
        LeetCode15Sum3 leetcode15 = new LeetCode15Sum3();

        for (List<Integer> integers : leetcode15.threeSum2(arr)) {
            System.out.println(integers);
        }
    }

    /**
     * 方法三 哈希表, set去重
     * Time complexity: O(n^2)
     *      Explanation: Sorting takes O(nlogn) & 'for' loop and 'while' loop takes O(n^2) together.
     *      But since O(n^2) > O(nlogn). Therefore, O(n^2).
     * Space complexity: O(n)
     *      Explanation: As the total elements are n and the space complexity will be some factor of n.
     *      Therefore, after removing constant, we are left with O(n).
     *
     * @param num
     * @return
     */
    public static List<List<Integer>> threeSum(int[] num) {
        //利用set去重的特性
        Set<List<Integer>> res = new HashSet<>();
        if (num.length == 0) return new ArrayList<>(res);
        Arrays.sort(num);
        //i, j (i+1), k (num.length-1),  i = len - 3, j will be len -2 and k will be len - 1.
        for (int i = 0; i < num.length - 2; i++) {
            int j = i + 1, k = num.length - 1;
            while (j < k) {
                int sum = num[i] + num[j] + num[k];
                if (sum == 0)
                    res.add(Arrays.asList(num[i], num[j++], num[k--]));//添加到res后, j++, k--
                else if (sum > 0)
                    k--;
                else
                    j++;
            }
        }
        return new ArrayList<>(res);

    }

    /**
     * 方法二 排序 + 双指针解法  时间复杂度 O(n^2)
     * @param num
     * @return
     */
    public List<List<Integer>> threeSum2(int[] num) {
        List<List<Integer>> res = new LinkedList<>();
        if (num == null || num.length <= 2) return res;

        Arrays.sort(num);//排序[-4,-1,-1,0,1,2]  时间复杂度 O(n log n)
        for (int i = 0; i < num.length - 2; i++) {  //时间复杂度 O(n^2)
            if (i == 0 || (i > 0 && num[i] != num[i-1])) { //num[i] = num[i-1]说明该数字会重复,结果会重复,所以要跳过
                int left = i + 1, right = num.length - 1, sum = 0 - num[i];//left 和 right 为两个指针,初始分别为数组两端, 往中间移动; 三数之和变成2数之和,
                while (left < right) { //不成立就返回for循环 i++   num[i]为固定的两数之和
                    if (num[left] + num[right] == sum) { //如果相等就添加到数组中
                        res.add(Arrays.asList(num[i], num[left], num[right]));
                        while (left < right && num[left] == num[left+1]) left++; //num[left] == num[left+1]会导致结果重复, 应该跳过 left++
                        while (left < right && num[right] == num[right-1]) right--; //num[right] == num[right-1]会导致结果重复, 应该跳过 right--
                        left++; right--;
                    } else if (num[left] + num[right] < sum) left++; //如果两数之和 < 固定的num[i],因为是排好序的, 就将左指针右移, 右指针不动
                    else right--;
                }
            }
        }
        return res;
    }


    /**
     * 方法一 这种暴力解法会超出时间限制  时间复杂度为 O(n^3)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
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
