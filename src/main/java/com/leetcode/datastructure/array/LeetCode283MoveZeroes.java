package com.leetcode.datastructure.array;

/**
 * 移动零
 *给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *      必须在原数组上操作，不能拷贝额外的数组。
 *      尽量减少操作次数。
 * Related Topics 数组 双指针
 *
 *  https://leetcode-cn.com/problems/move-zeroes/
 */
public class LeetCode283MoveZeroes {
    public static void main(String[] args) {
        LeetCode283MoveZeroes leetcode283 = new LeetCode283MoveZeroes();
        int[] numbers = new int[5];
        int[] numbs = {0,1,0,3,9,4};
        leetcode283.moveZeroes1(numbs);
        for (int num : numbs) {
            System.out.print(num + " ");
        }
    }

    /**
     * 方法一
     *  时间复杂度为 O(n), 空间复杂度为 O(1)
     *  一次遍历, 参考快排的思想(快速排序首先要确定一个待分割的元素做中间点x，然后把所有小于等于x的元素放到x的左边，大于x的元素放到其右边)
     *  使用了两个指针j , i  ; 只要nums[i] != 0 就与nums[j] 交换
     * @param nums
     * @return
     */
    public void moveZeroes(int[] nums) {
        int j = 0; //nums[j] 为记录为 0 数,
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                if (i != j){
                    nums[j] = nums[i]; //将为0 的和不为0的数依次做交换;
                    nums[i] = 0;
                }
                j++;
            }
        }
        //return nums;
    }

    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int pos = 0;
        for (int num: nums) {
            if (num != 0) nums[pos++] = num;
        }
        //上步的循环是把不是0的元素依次移动到数组的前边, 下步的循环是把数组后边空缺的位置补0;
        while (pos < nums.length) {
            nums[pos++] = 0;
        }

    }


}
