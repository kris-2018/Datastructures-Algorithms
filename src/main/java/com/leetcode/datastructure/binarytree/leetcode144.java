package com.leetcode.datastructure.binarytree;

import sun.rmi.server.InactiveGroupException;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class leetcode144 {

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

        /*TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);

        treeNode1.setRight(treeNode2);
        treeNode2.setLeft(treeNode3);*/


        for (Object o : preorderTraversal(treeNode1).toArray()) {
            System.out.print(o + " ");
        }


    }


    /**
     * 迭代法
     * 将遍历的元素放到 list 中, only right children are stored to stack.
     * 时间复杂度 O(n),n为树的节点数  空间复杂度 O(n)
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                result.add(root.data);
                stack.push(root.right);
                root = root.left;
            } else {
                root = stack.pop();
            }
        }
        return result;
    }

    /**
     *  迭代法
     *  stack push right left
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal0(TreeNode root) {
        List<Integer> resule = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                resule.add(node.data);
                stack.push(node.right);//先把right子树放到栈中, left子树先出, 先进行遍历
                stack.push(node.left);
            }
        }
        return resule;
    }



    /**
     *
     * 递归方法
     * Recursive method with List as returning value:
     * 时间复杂度：O(n)
     * @param node
     * @return
     */
    public static List<Integer> preorderTraversal2(TreeNode node) {
        List<Integer> pre = new LinkedList<>();
        if (node == null) return pre;              //1.terminator
        pre.add(node.data);                        //2. process current logic
        pre.addAll(preorderTraversal2(node.left)); //3. drill down
        pre.addAll(preorderTraversal2(node.right));
        return pre;
    }


    /**
     *
     * Recursive method with Helper method to have a List as paramater,
     * so we can modify the parameter and don't have to instantiate a new List at each recursive call
     * 时间复杂度：O(n)
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> pre = new LinkedList<>();
        preHelper(root, pre);
        return pre;
    }
    public static void preHelper(TreeNode root, List<Integer> pre) {
        if (root == null) return;
        pre.add(root.data);
        preHelper(root.left, pre);
        preHelper(root.right, pre);
    }
}
