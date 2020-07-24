package com.leetcode.datastructure.hashtable;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *  https://leetcode-cn.com/problems/two-sum/description/
 *
 *  Two Sum  ThreeSum  -- 都可以用map或set解决
 * 题目: 两数之和
 * 示例: 给定nums = [2,7,11,15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回[0, 1]
 *
 * ①暴力求解: [x, y] => x + y = 9
 * x在数组中循环一次,  y在x中嵌套循环; x和y不能重复使用
 * O(N^2)
 *
 * ②Set: x+y = 9  => y = 9 - x
 *        For: x: 0 -> array.length (x不能重复使用)  O(N)
 *             Set： 9 - x                           O(1)
 * O(N)
 *
 *
 */
public class LeetCode01TwoSum {
}
