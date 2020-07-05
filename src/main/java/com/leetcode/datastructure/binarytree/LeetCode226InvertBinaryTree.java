package com.leetcode.datastructure.binarytree;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树 Invert Binary Tree
 * 翻转一棵二叉树。
 * 示例：
 * 输入：
 *   4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 *   4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 * https://leetcode-cn.com/problems/invert-binary-tree/description/
 *
 */

public class LeetCode226InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(7);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(3);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(9);

        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);

        System.out.println("原二叉树为: " + treeNode1);
        //4 -> 2 -> 1 -> 3 -> 7  -> 6 -> 9
        System.out.println("翻转后为: " + invertTree0(treeNode1));
        //4 -> 7 -> 9 -> 6 -> 2 -> 3 -> 1
        //System.out.println(invertTree3(treeNode1));
    }

    /**
     * 方法一: 递归调用 bottom up
     * 时间复杂度就是 O(n)
     * 在最坏情况下栈内需要存放 O(h)个方法调用，其中 h 是树的高度。由于 h∈O(n)，可得出空间复杂度为 O(n) 。
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        //1. terminator
        if (root == null) return null;
        //2. process current logic 3. drill down
        TreeNode left = root.left, right = root.right; // It could be as a buffer.
        root.left = invertTree(right);
        root.right = invertTree(left);
        //4. return
        return root;
    }

    /**
     * 递归调用 top to down
     * @param root
     * @return
     */
    public static TreeNode invertTree0(TreeNode root) {
        if (root == null) {
            return null;
        }
        // swap the left right nodes
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // once done at root level, keep doing it at child nodes
        invertTree0(root.left);
        invertTree0(root.right);
        return root;
    }
    /**
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> stack = new LinkedList<>(); // Deques can also be used as LIFO (Last-In-First-Out) stacks
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }

    /**
     * 树中的每个节点都只被访问/入队一次，时间复杂度就是 O(n)
     * 空间复杂度是 O(n)
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); //Retrieves and removes the head of this queue
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left; // 对每个节点进行交换, 循环一次交换一次
            if (node.left != null) {
                queue.offer(node.left); //入队
            }
            if (node.right != null) {
                queue.offer(node.right); //入队
            }
        }
        return root;
    }
}
