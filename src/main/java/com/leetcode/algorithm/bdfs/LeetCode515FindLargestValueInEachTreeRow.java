package com.leetcode.algorithm.bdfs;

import com.leetcode.datastructure.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 515. 在每个树行中找最大值 Find Largest Value in Each Tree Row
 * 您需要在二叉树的每一行中找到最大的值。
 * <p>
 * 示例：
 * <p>
 * 输入:
 * <p>
 *    1
 *   / \
 *  3   2
 * / \   \
 * 5   3   9
 * <p>
 * 输出: [1, 3, 9]
 * <p>
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 */
public class LeetCode515FindLargestValueInEachTreeRow {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(3);
        TreeNode treeNode6 = new TreeNode(9);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode3.setRight(treeNode6);

        System.out.println(treeNode1.toString());
/*        System.out.println("DFS代码测试: ");
        for (Object o : largestValues(treeNode1).toArray()) {
            System.out.print(o + "\t");
        }*/

        System.out.println("BFS代码测试: ");
        for (Object o : largestValues1(treeNode1).toArray()) {
            System.out.print(o + "\t");
        }

    }

    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }
    /**
     *  DFS
     *  a simple pre-order traverse idea.
     *  Use depth to expand result list size and put the max value in the appropriate position.
     * @param root
     * @param res
     * @param depth
     */
    private static void helper(TreeNode root, List<Integer> res, int depth) {
        if (root == null) return;
            //expand list size
        if (depth == res.size()) {
            res.add(root.data);
        } else {
            //or set value 比较当前层元素的大小
            res.set(depth, Math.max(res.get(depth), root.data));
        }
        helper(root.left, res, depth + 1);
        helper(root.right, res, depth + 1);
    }

    /**
     *  BFS
     * @param root
     * @return
     */
    public static List<Integer> largestValues1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        //放进队列中
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE, n = queue.size();
            for (int i = 0; i < n; i++) {
                //删除队列中元素
                TreeNode node = queue.poll();
                max = Math.max(max, node.data);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}
