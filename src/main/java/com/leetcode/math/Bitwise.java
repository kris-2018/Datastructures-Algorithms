package com.leetcode.math;
import static com.leetcode.math.Hex.decimalToBinary;

/**
 *
 * 按位或 |
 * 按位与 &
 * 按位异或 ^
 * 按位取反 ~
 *
 */
public class Bitwise {

    public static void main(String[] args) {

        int a = 32;
        int b = 31;
        System.out.println(String.format("数字%d(%s)和数字%d(%s)的按位‘或’结果是%d(%s)",
                a, decimalToBinary(a), b, decimalToBinary(b), or(a, b), decimalToBinary(or(a, b)))); //获取十进制数53和35的按位“或”
        System.out.println(String.format("数字%d(%s)和数字%d(%s)的按位‘与’结果是%d(%s)",
                a, decimalToBinary(a), b, decimalToBinary(b), and(a, b), decimalToBinary(and(a, b)))); //获取十进制数53和35的按位“与”
        System.out.println(String.format("数字%d(%s)和数字%d(%s)的按位‘异或’结果是%d(%s)",
                a, decimalToBinary(a), a, decimalToBinary(a), xor(a, a), decimalToBinary(xor(a, a)))); //获取十进制数53和35的按位“异或”


        System.out.println(2^31);
    }


    public static int or(int num1, int num2) {
        return (num1 | num2);
    }

    public static int and(int num1, int num2) {
        return (num1 & num2);
    }
    public static int xor(int num1, int num2) {
        return (num1 ^ num2);
    }
}
