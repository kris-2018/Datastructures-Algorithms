package com.leetcode.algorithm.bdfs.heuristicsearch;

import java.util.Arrays;

/**
 * 37. 解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。一个数独的解法需遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * 一个数独。
 * 答案被标成红色。
 *
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 *
 */
public class Leetcode37SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku2(board);
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + ' ');
            }
            System.out.println();
        }
    }



    public static void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }
    private static boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = 'c';
                            if (solve(board))
                                return true;
                            else board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '.' && board[i][col] == c) return false;
            if (board[row][i] != '.' && board[row][i] == c) return false;
            if (board[3 * (row/3) + (i/3)][3 * (col/3) + (i%3)] != '.' && board[3 * (row/3) + (i/3)][3 * (col/3) + (i%3)] == c)
                return false;
        }
        return true;
    }


    /* 方法二 */
    public static void solveSudoku2(char[][] board) {
        dfs(board,0);
    }
    private static boolean dfs(char[][] board, int d) {
        if (d==81) return true; //found solution
        int i=d/9, j=d%9;
        if (board[i][j]!='.') return dfs(board,d+1);//prefill number skip

        boolean[] flag=new boolean[10];
        validate(board,i,j,flag);
        for (int k=1; k<=9; k++) {
            if (flag[k]) {
                board[i][j]=(char)('0'+k);
                if (dfs(board,d+1)) return true;
            }
        }
        board[i][j]='.'; //if can not solve, in the wrong path, change back to '.' and out
        return false;
    }
    private static void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag,true);
        for (int k=0; k<9; k++) {
            if (board[i][k]!='.') flag[board[i][k]-'0']=false;
            if (board[k][j]!='.') flag[board[k][j]-'0']=false;
            int r=i/3*3+k/3;
            int c=j/3*3+k%3;
            if (board[r][c]!='.') flag[board[r][c]-'0']=false;
        }
    }
}
