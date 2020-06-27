package com.leetcode.datastructure.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class leetcode94 {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(27);
        TreeNode treeNode2 = new TreeNode(14);
        TreeNode treeNode3 = new TreeNode(10);
        TreeNode treeNode4 = new TreeNode(19);
        TreeNode treeNode5 = new TreeNode(35);
        TreeNode treeNode6 = new TreeNode(31);
        TreeNode treeNode7 = new TreeNode(42);

        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode5);
        treeNode2.setLeft(treeNode3);
        treeNode2.setRight(treeNode4);
        treeNode5.setLeft(treeNode6);
        treeNode5.setRight(treeNode7);

        for (Object o : inorderTraversal3(treeNode1).toArray()) {
            System.out.print(o + " ");
        }
    }

    /**
     * 时间复杂度：O(n)
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left; //一直循环遍历找到最 左子节点
            } else {
                result.add(stack.peek().data);//找到最 左子节点 添加, 然后下一步找 出栈的右节点
                root = stack.pop().right;
            }
        }
        return result;
    }

    /**
     * 时间复杂度：O(n)
     * 遍历 2 遍
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.data);
            cur = cur.right;
        }
        return result;
    }

    /**
     * 递归方法
     * 时间复杂度为 O(n) 递归函数 T(n) = 2 * T(n/2)+1 , 空间复杂度为 O(n)
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        helper(root, res);
        return res;
    }
    public static void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        res.add(root.data);
        helper(root.right, res);
    }

}
