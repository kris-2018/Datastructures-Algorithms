package com.leetcode.math;

public class Bit {

    public static void main(String[] args) {
        int num = 53;
        int m = 1;
        // <<
        System.out.println(String.format("数字%d的二进制向左移 %d 位是 %d", num,m,leftShift(num,m))); //测试左移
        // >>>
        System.out.println(String.format("数字%d的二进制向右移 %d 位是 %d", num,m,rightShift(num,m))); //测试左移


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
     * @param num
     * @param m
     * @return
     */
    public static int rightShift(int num, int m) {
        return num >>> m;
    }



}
