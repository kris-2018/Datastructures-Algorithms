package com.leetcode.datastructure.binarytree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 */
public class LeetCode104MaxDepthBinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(5);


        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode3.setRight(treeNode4);

        System.out.println(maxDepth(treeNode1));

    }

    /**
     * 递归
     * 时间复杂度为 O(n)
     * @param root
     * @return
     */
    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
         int lf = maxDepth(root.left);
         int rt = maxDepth(root.right);
         int max = Math.max(lf,rt);
         //为什么 + 1, 递归 进到叶子节点最终root.next = null -> return 0, 出一层 就加 1.
        return  max + 1;
    }


    /**
     * BFS
     * @param root
     * @return
     */
    private static int maxDepth3(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) { //循环一轮即遍历一个二叉树, 把左子树 右子树放到 stack 中, count++
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll(); //出队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            count++;
        }
        return count;
    }

    /**
     * DFS
     * @param root
     * @return
     */
    private static int maxDepth4(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int tmp = value.pop();
            max = Math.max(tmp, max);
            if (node.left != null) {
                stack.push(node.left);
                value.push(tmp + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                value.push(tmp + 1);
            }
        }
        return max;
    }
}
