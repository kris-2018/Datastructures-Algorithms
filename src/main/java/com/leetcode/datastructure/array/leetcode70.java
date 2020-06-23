package com.leetcode.datastructure.array;

/**
 * 70 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 *     https://leetcode.com/problems/climbing-stairs
 *
 *
 * 1    1
 * 2    2(1 1, 2)
 * 3    3(1 1 1, 1 2, 2 1) |  f(2) + f(1)
 * 4    f(2) + f(3) |跨两步，跨一步
 * 蕴含的思想找最近重复字问题:  if else  for while  recursion
 * n级台阶，只能从n - 1阶走 或者从 n - 2阶走
 *
 * 归纳法： f(n) = f(n-2) + f(n-1) | fibonacci斐波拉契数列
 * 解法：递归 2n^2
 *      循环  for i = 1; i <= n; ++i{ //用一个数组
 *          a[i] = a[i-1] + a[i-2];
 *      }
 *
 */
public class leetcode70 {

    public static void main(String[] args) {
        leetcode70 leetcode70 = new leetcode70();
        System.out.println(leetcode70.climbStairs(4));
    }

    /**
     * 归纳   时间复杂度O(n), 空间复杂度 O(1)
     * @param n
     * @return
     */
    public int climbStairs(int n){
            if (n <= 2 ) return n;
            int f1 = 1, f2 = 2, f3 = 3; //保存最近的3个值，然后不断的往前累加;
            for (int i = 3; i < n + 1; i++){
                f3 = f2 + f1; //f(n) = f(n - 2) + f(n - 1)
                f1 = f2;// 先 f1 = f2; 后 f2 = f3; 这个顺序不能交换,  如果交换了就是将 f(n-2) = f(n-1) = f(n) 的赋值
                f2 = f1;
            }
            return f3;
    }

    /**
     * 数学公式  时间复杂度为 O(logn)
     * 本题是斐波那契数列, 可用斐波拉契数列公式即可解决:
     *  Fn = 1/Math.sqrt(5)[ ( 1+Math.sqrt(5)  / 2 )^n - ( 1-Math.sqrt(5)  / 2 )^n ]
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib_n = Math.pow((1 + sqrt_5) / 2, n + 1) - Math.pow((1 - sqrt_5) / 2, n + 1);
        return (int)(fib_n / sqrt_5);
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        return n;
    }

    /**
     * 递归方法
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        return n;
    }
}
