package com.leetcode.algorithm.bitoperation;

/**
 *338. 比特位计数 counting-bits
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 *  https://leetcode-cn.com/problems/counting-bits/description/
 *
 */
public class LeetCode338CountingBits {

    public static void main(String[] args) {
        for (int i : countBits(5)) {
            System.out.print(i + "\t");
        }
    }

    /**
     * Take number X for example, 10011001.
     * Divide it in 2 parts:
     * <1>the last digit ( 1 or 0, which is " i&1 ", equivalent to " i%2 " )
     * <2>the other digits ( the number of 1, which is " f[i >> 1] ", equivalent to " f[i/2] " )
     *
     *
     * 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     * 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
     *
     * @param num
     * @return
     */
    public static int[] countBits(int num) {
        int[] arr = new int[num + 1];
        //从1开始, 不能从0
        for (int i = 1; i <= num; i++) {
            //(i >> 1即 i/2; i & 1 偶数为0,奇数为1) 即 f[i] = f[i / 2] + i % 2, Divide it in 2 parts
            arr[i] = arr[i >> 1] + (i & 1);// or  -> arr[i] += arr[i & (i - 1)] + 1;
        }
        return arr;
    }
}
