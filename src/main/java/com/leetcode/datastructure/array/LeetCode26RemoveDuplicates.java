package com.leetcode.datastructure.array;

/**
 * 26. 删除排序数组中的重复项   Remove Duplicates from Sorted Array
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 *  nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 *在函数里修改输入数组对于调用者是可见的。
 * 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *  https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *  https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class LeetCode26RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {4, 5, 5, 5, 5, 5};
        LeetCode26RemoveDuplicates leetcode26 = new LeetCode26RemoveDuplicates();
        System.out.println(leetcode26.removeDuplicates(nums));

    }

    /**
     * Amazing method 时间复杂度 O(n), 空间复杂度 O(1); 如果数组长度为 0, 输出为 0
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i == 0 || n > nums[i - 1]) //当前位 与 前一位 做比较
                nums[i++] = n; //将后一位 赋 给 前一位  注意点 i++ ！！
        return i; //返回i 的次数
    }

    /**
     *  双指针法  i 是慢指针，而 j 是快指针; 时间复杂度 O(n)  空间复杂度 O(1)
     *  这个解法的前提是排好序的数组
     *  当 nums[i] == nums[j], 就增加 j 以跳过重复项。
     *  当 nums[j] != nums[i], num[i+1] = num[j]
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++){
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }//如果 nums[i] = num[j], 就j++
        }
        return i + 1;
    }

}


