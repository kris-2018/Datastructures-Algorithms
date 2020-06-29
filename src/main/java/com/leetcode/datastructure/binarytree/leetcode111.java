package com.leetcode.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2
 *
 *  https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 */
public class leetcode111 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(5);

        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode3.setRight(treeNode4);

        System.out.println(minDepth2(treeNode1));

    }

    /**
     * DFS
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? (left + right + 1) : Math.max(left, right) + 1;
    }

    public static int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0;i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left == null && curNode.right == null) {
                    return level;
                }
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            level++;
        }
        return level;
    }

}
