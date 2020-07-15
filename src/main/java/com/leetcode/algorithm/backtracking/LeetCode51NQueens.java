package com.leetcode.algorithm.backtracking;


import java.util.*;

/**
 * 51. N皇后 N-Queens
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * PS：皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位。
 * 这个问题本质上跟全排列问题差不多，决策树的每一层表示棋盘上的每一行；每个节点可以做出的选择是，在该行的任意一列放置一个皇后。
 *
 *
 * 上图为 8 皇后问题的一种解法。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 *
 *
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 *
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。
 * https://leetcode-cn.com/problems/n-queens/
 */
public class LeetCode51NQueens {

    public static void main(String[] args) {
        final LeetCode51NQueens leetCode51NQueens = new LeetCode51NQueens();
        for (Object o : leetCode51NQueens.solveNQueens1(4).toArray()) {
            System.out.println(o);
        }

        char[][] board = new char[4][4];
        for (char[] chars: board) {
            Arrays.fill(chars, '.');
            System.out.println(chars);
        }
        System.out.println(board.length);
        System.out.println(board[3].length);
    }


    List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {

        if (n <= 0) return null;
        res = new LinkedList<>();
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        char[][] board = new char[n][n];
        for (char[] chars : board) Arrays.fill(chars, '.');
        backtrack(board, 0);
        return res;
    }
    /**
     * 路径:       board中小于row的那些行都已经成功放置了皇后
     * 可选择列表: 第row行的所有列都是放置Q的选择
     * 结束条件:   row超过board的最后一行
     *
     * @param board
     * @param row
     */
    private void backtrack(char[][] board, int row) {
        //1. terminator 定义一个charToString方法
        if (row == board.length) {
            res.add(charToString(board));
            return;
        }
        //2. for循环列-迭代选择列表
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            //排除不合法选择
            if (!isValid(board, row, col)) continue;
            // 做选择
            board[row][col] = 'Q';
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }
    private static List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }
    /* 是否可以在 board[row][col] 放置皇后？ 将不符合条件的情况剪枝 */
    private boolean isValid(char[][] board, int row, int col) {
        int rows = board.length;
        //检查列是否有皇后互相冲突
        for (char[] chars : board){
            if (chars[col] == 'Q')
                return false;
        }
        //  检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }


    /**
     * 对上边的优化
     * 优化isValid的查询，通过3个set来分别记录列、主对角线、副对角线上Q的情况，减少迭代的查询
     * Key值：colIndex, [r-c], [r + c] 作为set的key
     */
    private List<List<String>> result = new LinkedList<>();
    private Set<Integer> colSet = new HashSet<>();
    private Set<Integer> masterSet = new HashSet<>();
    private Set<Integer> slaveSet = new HashSet<>();
    public List<List<String>> solveNQueens1(int n) {
        char[][] board = new char[n][n];
        for (char[] chars: board) {
            Arrays.fill(chars, '.');
        }
        backtrack2(board, 0);
        return result;
    }

    private void backtrack2(char[][] board, int row) {
        if (row == board.length) {
            result.add(charToString(board));
            return;
        }
        for (int col = 0; col < board[row].length; col++) {
            if (!isValid2(board, row, col)) continue;
            updateRecords2(board, row, col);
            backtrack2(board, row + 1);
            updateRecords2(board, row, col);
        }
    }

    private void updateRecords2(char[][] board, int row, int col) {
        if (colSet.contains(col)) {
            board[row][col] = '.';
            colSet.remove(col);
            masterSet.remove(row - col);
            slaveSet.remove(row + col);
        } else {
            board[row][col] = 'Q';
            colSet.add(col);
            masterSet.add(row- col);
            slaveSet.add(row + col);
        }
    }

    private boolean isValid2(char[][] board, int row, int col) {
        return !colSet.contains(col) && !masterSet.contains(row - col) && !slaveSet.contains(row + col);
    }


    public static List<String[]> solveNQueens2(int n) {
        List<String[]> res = new ArrayList<>();
        helper(0, new boolean[n], new boolean[2 * n], new boolean[2 * n],new String[n], res);
        return res;
    }

    private static void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2, String[] board, List<String[]> res) {
        if (r == board.length) res.add(board.clone());
        else {
            for (int c = 0; c < board.length; c++) {
                int id1 = r - c + board.length, id2 = 2 * board.length - r - c - 1;
                if (!cols[c] && !d1[id1] && !d2[id2]) {
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.');
                    row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = true;
                    d1[id1] = true;
                    d2[id2] = true;
                    helper(r + 1, cols, d1, d2, board, res);
                    cols[c] = false;
                    d1[id1] = false;
                    d2[id2] = false;
                }
            }
        }
    }





}
