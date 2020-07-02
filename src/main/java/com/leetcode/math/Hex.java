package com.leetcode.math;

import java.math.BigInteger;

public class Hex {

    public static void main(String[] args) {
        System.out.println(decimalToBinary(10));
    }


    /**
     * 十进制转换为其他进制 如 二进制
     * @param decimalSource
     * @return
     */
    public static String decimalToBinary(int decimalSource) {
        BigInteger bi = new BigInteger(String.valueOf(decimalSource));//转换为 B
        return bi.toString(2);  //参数2指定的是转化成二进制
    }

    /**
     * 十进制转换为二进制
     * @param binarySource
     * @return
     */
    public static int binaryToDecimal(String binarySource) {
        final BigInteger bi = new BigInteger(binarySource, 2);//转换为BigInteger类型，参数2指定的是二进制 return Integer.parseInt(bi.toString());
        return Integer.parseInt(bi.toString()); //默认转换成十进制
    }


}
