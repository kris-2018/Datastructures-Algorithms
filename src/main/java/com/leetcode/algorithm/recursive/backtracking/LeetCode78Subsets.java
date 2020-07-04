package com.leetcode.algorithm.recursive.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集  Subsets
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * https://leetcode.com/problems/subsets/
 *
 */
public class LeetCode78Subsets {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        for (Object o : subsets0(nums).toArray()) {
            System.out.println(o);
        }
    }

    static List<List<Integer>> output = new ArrayList();
    static int n, k;

    public static void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == k)
            output.add(new ArrayList(curr));

        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

    public static List<List<Integer>> subsets0(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            backtrack(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }

    /**
     * 回溯法
     * 幂集是所有长度从 0 到 n 所有子集的组合。从序列中生成幂集, 遍历 子集长度，通过 回溯 生成所有给定长度的子集。
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }
    /* 定义一个回溯 算法,list,  将tempList = new ArrayList<Integer>() 添加到 list 中 */
    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList)); // [] 也是一种子集
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);//遍历nums数组, 将每个元素添加到tempList中, 返回回调
            backtrack(list, tempList, nums, i + 1); //[]  1  1,2   1,2,3  1,3  2  2,3  3
            tempList.remove(tempList.size() - 1); //移除下标的元素 , 这步为什么i start 都递减了。 i<nums.length 否-> i = start 由 3 -> 2     递归  ?   1 2 3  |  3 2 1
        }

    }

    /**
     * 递归
     * Each element can either be picked or not be picked, so by the idea of depth first search
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        helper(result, nums, cur, 0);
        return result;
    }
    private static void helper(List<List<Integer>> result, int[] nums, LinkedList<Integer> cur, int idx) {
        if (idx == nums.length) {
            result.add(new LinkedList<Integer>(cur));
            return;
        }
        cur.addLast(nums[idx]);
        helper(result, nums, cur, idx + 1);
        cur.removeLast();
        helper(result, nums, cur, idx + 1);
    }

    /**
     *  bit manipulation 二进制
     *  位 操作
     *
     * []
     * [1]
     * [2]
     * [1, 2]
     * [3]
     * [1, 3]
     * [2, 3]
     * [1, 2, 3]
     *
     * 0 & 1 ->0, 1 & 1 ->1,
     * 2 & 1-> 0, 3 & 1->1,
     * 4 & 1-> 0, 5 & 1->1,
     * 6 & 1-> 0, 7 & 1->1,
     * 8 & 1-> 0, 9 & 1->1,
     * 10 & 1-> 0
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int a = 1 << nums.length; // 1 << 3, 1*2^3
        for (int i = 0; i < a; i++) { //1 << nums.length,左移n位即将数字乘以2^n
            List<Integer> subSet = new ArrayList<>();//内层循环,每次都要重新new一个数组
            for (int j = 0; j < nums.length; j++) {
                int b = (i >> j); // i >> j,i左右移(算术,保持符号位不变) j位即i / 2^j并求整数商的操作
                int c = b & 1;//1二进制为1,   b&1 按位与操作, 偶数按位与为0, 奇数按位与为1
                if (c == 1) { //or if((i & (1<<j)) != 0)   和1作 按位与 操作
                    subSet.add(nums[j]);
                }
            }
            result.add(subSet);
        }
        return result;
    }

    /**
     * 迭代法
     * While iterating through all numbers, for each new number, we can either pick it or not pick it
     * 1, if pick, just add current number to every existing subset.
     * 2, if not pick, just leave all existing subsets as they are.
     * We just combine both into our result.
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int n: nums){
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subSet = new ArrayList<>(result.get(i));
                subSet.add(n);
                result.add(subSet);
            }
        }
        return result;
    }



}
