package com.leetcode.algorithm.recursive.divideconquer;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 *
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 *
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *
 * 说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * Java 代码中 int32 变量 n∈[-2147483648, 2147483647], 因此当 n = -2147483648时执行 n = −n会因越界而赋值出错。
 *
 * https://leetcode-cn.com/problems/powx-n/
 *
 */
public class LeetCode50Powxn {
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        //System.out.println(myPow(2, -5));
        System.out.println(pow(2, 10));
        //System.out.println(pow3(2, 5 ));
    }

    /**
     * 快速幂 + 递归
     *  pow(2, 1024) --> Infinity
     * 快速幂算法 本质就是分治算法,
     * 当我们要计算 x^n 时，我们可以先递归地计算出 y = x^[n/2], [n/2]表示对 n/2 进行下取整
     * 根据递归计算的结果，如果 n 为偶数，那么 x^n = y^2
     *                  如果 n 为奇数，那么 x^n = y^2 * x
     * 递归的边界为 n = 0，任意数的 0次方均为 1
     *
     * 由于每次递归都会使得指数减少一半，因此递归的层数为 O(log n)，算法可以在很快的时间内得到结果。
     *
     * 时间复杂度：O(logn)，即为递归的层数。
     * 空间复杂度：O(logn)，即为递归的层数。这是由于递归的函数调用会使用栈空间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     *
     * @param x
     * @param n
     * @return
     */
    public static double pow(double x, int n) {
        if (n == 0) return 1; //递归结束条件
        if (n < 0) {
            //n = -n;  n∈[-2147483648, 2147483647], 因此当 n = -2147483648时执行 n = −n会因越界而赋值出错。
            x = 1 / x;
            return (n % 2 == 0) ? pow(x * x, -(n/2)) : x * pow(x * x, -(n / 2));
        }
        return (n % 2 == 0) ? pow(x * x, n / 2) : x * pow(x * x, n / 2); //   n / 2取商整数,
    }

    /**
     * O(log n)
     */
    public static double pow2(double x, int n) {
        double ans = 1;
        for (long i = Math.abs((long) n); i > 0; i = i >> 1, x *= x) {
            if ((i & 1) == 1) { // （i & 1）  按位 与 运算符, 转化为二进制 & 1 做与运算, 同时为1时才为1, 所以奇数与1作与运算 结果肯定为1
                ans *= x;
            }
        }
        return n > 0 ? ans : 1 / ans;
    }


    /**
     * scan from lower bit to higher bit, all 31 bit in int n will be scanned.
     *
     * x will be update instead of double answer
     * when we meet with Integer.MIN_VALUE, we should multiply x ahead.
     *
     * @param x
     * @param n
     * @return
     */
    public static double pow3(double x, int n) {
        if (x == 0) return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = ans * x;
            }
            x = x * x;
            n >>= 1; //  >> 表向右移位运算, >>= 代表的是向右移位运算时将运算的结果同时赋值给原值; 将n转化为二进制向右移 1 位, 并将结果赋给原值
        }
        return ans;
    }
}
