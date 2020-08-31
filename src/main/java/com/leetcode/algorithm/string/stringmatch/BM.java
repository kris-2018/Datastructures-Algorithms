package com.leetcode.algorithm.string.stringmatch;

/**
 * BM 算法
 */
public class BM {

    private static final int SIZE = 256;  // 全局变量或成员变量
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;  // 初始化 bc
        }
        for (int i = 0; i < m; i++) {
            int ascii = (int) b[i]; // 计算 b[i] 的 ASCII 值
            bc[ascii] = i;
        }
    }

    public int bm(char[] a, int n, char b, int m) {

    }

}
