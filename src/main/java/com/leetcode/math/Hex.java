package com.leetcode.math;
import java.math.BigInteger;

/**
 *
 * 进制之间的转换
 *  十进制转换为其他进制 如 二进制  除法
 *  二进制转换成十进制 如（ 110101  < = >  1*2^5 + 1*2^4 + 0*2^3 + 1*2^2 + 0*2^1 + 1*2^0 = 32 + 16 + 0 + 4 + 0 + 1 = 53）
 *
 */
public class Hex {

    public static void main(String[] args) {

        System.out.println("十进制转换为二进制: " + decimalToBinary(50));
        System.out.println("十进制转换为二进制: " + decimalToBinary(48));

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
