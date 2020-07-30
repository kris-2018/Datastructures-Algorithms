package com.leetcode.algorithm.binarysearch;

/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 *
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 *
 */
public class LeetCode74SearchA2dMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int target = 3;
        System.out.println(searchMatrix(matrix, target));
    }

    /**
     *  binary search. 0 ms
     *  index / numberOfCols => which row
     *  index % numberOfCols => which col
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0].length == 0) return false;
        int start = 0, rows = matrix.length, cols = matrix[0].length;
        //将matrix转换为一维数组array, index/cols --> row; index%cols --> col
        int end = rows * cols - 1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            int mid_value = matrix[mid/cols][mid%cols];
            if (mid_value > target) {
                end = mid - 1;
            } else if (mid_value < target) {
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
