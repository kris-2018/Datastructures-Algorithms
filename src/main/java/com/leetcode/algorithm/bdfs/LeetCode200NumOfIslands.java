package com.leetcode.algorithm.bdfs;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1:
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 * <p>
 * https://leetcode-cn.com/problems/number-of-islands/
 *
 *
 * 对于网格上的 DFS，可参考二叉树的 DFS，写出网格 DFS
 * 网格结构中的格子有多少相邻结点？答案是上下左右四个。对于格子 (r, c) 来说（r 和 c 分别代表行坐标和列坐标），
 *      四个相邻的格子分别是 (r-1, c)、(r+1, c)、(r, c-1)、(r, c+1)。即网格结构是「四叉」的。
 *
 *
 *
 */
public class LeetCode200NumOfIslands {
    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] grid2 = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        char[][] grid3 = new char[][]{{'1', '1', '1'}, {'0', '1', '0'}, {'1', '0', '0'}, {'1', '0', '1'}};

        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfsFill(grid, i, j); // 调用函数的次数即count即岛屿的数量;
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfsFill(char[][] grid, int i, int j) {
        //1. 坐标(i, j)超出了网格范围, 直接返回; grid[i][j] 出现数组下标越界;  超出网格范围的格子 <=> 二叉树的root==null, 网格边缘的格子 <==> 二叉树的叶结点
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';//将当前网格赋为0, 表示已经遍历过, 避免重复
        //遍历下上右左四个方向
        dfsFill(grid, i + 1, j);
        dfsFill(grid, i - 1, j);
        dfsFill(grid, i, j + 1);
        dfsFill(grid, i, j - 1);
    }
}
