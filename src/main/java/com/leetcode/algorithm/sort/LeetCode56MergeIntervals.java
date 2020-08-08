package com.leetcode.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class LeetCode56MergeIntervals {

    public static void main(String[] args) {
        //int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals = {{3, 5}, {1, 4}};
        for (int[] nums : merge(intervals)) {
            for (int num : nums) {
                System.out.print(num + "\t");
            }
        }
    }

    /**
     * Sorting takes O(n log(n)) and merging the intervals takes O(n).
     * So, the resulting algorithm takes O(n log(n)).
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        // Sort by ascending starting point;  used a lambda comparator
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> result = new ArrayList<>();
        int[] temp = intervals[0];//获取以首位下标排序的n个数组的第一个
        result.add(temp);
        for (int[] interval : intervals) {
            if (interval[0] <= temp[1]) // Overlapping intervals, move the end if needed
                temp[1] = Math.max(temp[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                temp = interval;
                result.add(temp);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
