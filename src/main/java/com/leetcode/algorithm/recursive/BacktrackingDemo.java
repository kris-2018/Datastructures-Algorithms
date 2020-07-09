package com.leetcode.algorithm.recursive;


/**
 *回溯算法框架。解决一个回溯问题，实际上就是一个决策树的遍历过程。你只需要思考 3 个问题：
 *      1、路径：也就是已经做出的选择。
 *      2、选择列表：也就是你当前可以做的选择。
 *      3、结束条件：也就是到达决策树底层，无法再做选择的条件。
 *
 * 链接：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
 * 来源：力扣（LeetCode）
 *
 * 回溯算法框架代码:
 *
 * result = []
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         做选择
 *         backtrack(路径, 选择列表) -- 递归
 *         撤销选择
 *
 *
 */
public class BacktrackingDemo {


}
