package com.leetcode.math;

/**
 * 二进制的位操作
 *  1.移位操作
 *    二进制左移一位，就是将数字翻倍。
 *    二进制右移一位，就是将数字除以2并求整数商。
 *  2.逻辑操作
 *    "或 |": 参与操作的位中只要有一个是1, 最终结果就是1.
 *    "与 &": 参与操作的位中必须全都是1, 最终结果才是1, 否则就是0.
 *    "异或 ^": 参与操作的位相同, 最终结果就是0, 否则为1.
 *    "按位取反 ~":  0011 -------- =>  1100
 *
 */
public class Bit {

    public static void main(String[] args) {

        int num = -53;
        int m = 1;
        // <<
        System.out.println(String.format("数字%d的二进制向左移 %d 位是 %d", num,m,leftShift(num,m))); //测试左移
        // >>>
        System.out.println(String.format("数字%d的二进制逻辑右移 %d 位是 %d", num,m,logicRightShift(num,m))); //测试逻辑右移
        // >>
        System.out.println(String.format("数字%d的二进制算术右移 %d 位是 %d", num,m,arithmeticRightShift(num,m))); //测试算术逻辑右移


    }


    /**
     * 向左移位
     * @param num
     * @param m
     * @return
     */
    public static int leftShift(int num, int m) {
        return num << m;
    }

    /**
     * 向右移位
     * 算术右移 >> 考虑符号位，右移1位，若符号位为1，则左边补1；若符号位为0，则左边补0。
     * 逻辑右移 >>> 不考虑符号位，左边统一补 0
     * @param num
     * @param m
     * @return
     */
    public static int logicRightShift(int num, int m) {
        return num >>> m;
    }

    /** 算术右移 >>  */
    public static int arithmeticRightShift(int num, int m) {
        return num >> m;
    }



}
