package com.leetcode.datastructure.array;

/**
 * 189. 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 *
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 *  https://leetcode.com/problems/rotate-array
 */
public class LeetCode189RotateArray {

    public static void main(String[] args) {
        LeetCode189RotateArray leetcode189 = new LeetCode189RotateArray();
        int[] res = {1,2,3,4};
        leetcode189.rotate(res, 2);
        for (int i : res) {
            System.out.print(i + "\t");
        }
    }

    /** 方法一:
     * 反转  时间复杂度 O(n), 空间复杂度 O(1)
     * 将所有元素反转。然后反转前 k 个元素，再反转后面 n−k 个元素，就能得到想要的结果。
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length; //k = k % nums.length 取余数  这步主要是为了防止 k 超过数组的长度
        reverse(nums, 0, nums.length - 1); //先将所有的数都反转, 收尾交换, 往中间夹
        reverse(nums, 0, k - 1); //再将数组从0位到 k-1位交换
        reverse(nums, k, nums.length - 1); //最后将k k位 到 末位交换, 两次交换相当于位置没变化.
    }
    //数组中 两两 交换
    public void reverse(int[] nums, int start, int end) {
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;  // 两两交换元素 位置,  往中间收缩夹紧
            start++;
            end--;
        }
    }

    /**
     * 方法二
     * 时间复杂度 O(n), 空间复杂度 O(n)
     * 使用额外的空间 不符合题意
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        int[] new_nums = new int[nums.length];
        //把需要旋转的元素放到新的数组中
        for (int i = 0; i < nums.length; i++) {
            new_nums[(i + k) % nums.length] = nums[i]; // (i+k) % nums.length  2 3 0 1
        }
        //将旋转好的数组依次赋值给nums数组
        for (int i = 0; i < nums.length; i++) {
            nums[i] = new_nums[i];
        }
    }

    /**
     * 暴力求解
     * 旋转 k 次, 每次将数组旋转一个元素
     * @param nums
     * @param k
     */
    public void  rotate2(int[] nums, int k) {
        int temp, last;
        for (int i = 0; i < k; i++) {
            last = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = last;
                last = temp;
            }
        }
    }

}
