package com.leetcode.math;

/**
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 */
public class LeetCode51NQueens {

    private int size;
    private int count;
    private void solve(int row, int ld, int rd) {
        if (row == size) {
            count++;
            return;
        }
        int pos = size & (~(row | ld | rd));
        while (pos != 0) {
            int p = pos & (-pos);
            pos -= p;
            solve(row | p, (ld | p) << 1, (rd | p) >> 1);
        }
    }
    /** NQueens的 n种解法 */
    public int totalNQueens(int n) {
        count = 0;
        size = (1 << n) - 1;
        solve(0,0,0);
        return count;
    }

    public static void main(String[] args) {
        final LeetCode51NQueens nQueens = new LeetCode51NQueens();
        System.out.println(nQueens.totalNQueens(8));
    }

}
