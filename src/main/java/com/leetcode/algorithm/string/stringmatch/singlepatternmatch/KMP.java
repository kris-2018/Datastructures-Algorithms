package com.leetcode.algorithm.string.stringmatch.singlepatternmatch;

/**
 * 字符串模式匹配算法---KMP算法
 * https://www.cnblogs.com/fuck1/p/6059736.html
 * KMP算法可以在O(n+m)的时间数量级上完成串的模式匹配操作，其基本思想是：每当匹配过程中出现字符串比较不等时，不需回溯指针，而是利用已经得到的“部分匹配”结果将模式向右“滑动”尽可能远的一段距离，继续进行比较
 */
public class KMP {
    public static void main(String[] args) {
        char[] a = {'a', 'c', 'd', 'a', 'b', 'a', 'b', 'a', 'c', 'd'};
        int n = 10;
        char[] b = {'a', 'b', 'a', 'b', 'a', 'c', 'd'}; //next:{-1,-1,0,1,2,-1,-1}
        int m = 7;
        System.out.println(kmp(a, n, b, m));

    }

    /**
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串的长度
     * @return 匹配后的主串起始下标
     */
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && a[i] != b[j]) { // 一直找到 a[i] 和 b[j]
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                j++;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }
    /**
     * next数组,失效函数
     * 按照下标 i 从小到大，依次计算 next[i]，并且 next[i] 的计算通过前面已经计算出来的 next[0]，next[1]，……，next[i-1]来推导
     * @param b 表示模式串
     * @param m 表示模式串的长度
     * @return next数组也叫失效函数(failure function),用来存储模式串中每个前缀（这些前缀都有可能是好前缀）的最长可匹配前缀子串的结尾字符下标
     *  数组的下标是每个前缀结尾字符下标, 数组的值是这个前缀的最长可以匹配前缀子串的结尾字符下标
     */
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; i++) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }

}
