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
public class LeetCode111MinDepthBinaryTree {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(0);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(5);

        //treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        //treeNode3.setRight(treeNode4);

        System.out.println(minDepth(treeNode1));

    }

    /**
     * DFS 递归
     * 时间复杂度：我们访问每个节点一次，时间复杂度为 O(N)
     * 空间复杂度：最坏情况下，整棵树是非平衡的，例如每个节点都只有一个孩子，递归会调用 N（树的高度）次，因此栈的空间开销是 O(N) 。
     *                                        但在最好情况下，树是完全平衡的，高度只有 log(N)，因此在这种情况下空间复杂度只有 O(log(N)) 。
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //left == 0 || right == 0 , 是判断是否是只有一个节点这种情况, 如果左子树都为空, 则它的最低深度不是0, 以右子树的深度为准。
        return (left == 0 || right == 0) ? (left + right + 1) : Math.min(left, right) + 1;
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
