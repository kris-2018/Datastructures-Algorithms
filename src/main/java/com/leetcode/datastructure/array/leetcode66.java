package com.leetcode.datastructure.array;

/**
 * 66. 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 *  https://leetcode.com/problems/plus-one/
 *
 */
public class leetcode66 {
    public static void main(String[] args) {
        int[] digits = {9,8};
        for (int i : plusOne(digits)) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 分两种情况:
     * 1. 除 9 之外的数加 1
     * 2. 数字 9 进位
     * 时间复杂度为 O(n) 空间复杂度为 O(n)
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        //除 9 之外的数加 1
        for (int i = digits.length - 1; i >= 0; i--){
            if (digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        //这种情况是只有 9,9,9,9 等都为9的这种情况, 加1得10, 进一位个位数为0
        int[] newNumber = new int[digits.length + 1];
        newNumber[0] = 1;
        return newNumber;
    }
}
