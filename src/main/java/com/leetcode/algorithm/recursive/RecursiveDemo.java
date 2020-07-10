package com.leetcode.algorithm.recursive;

import com.leetcode.datastructure.binarytree.TreeNode;

import java.util.*;

/**
 * 递归代码模板
 * 三个思维要点:
 * 1. 不要人肉递归(画递归状态树)
 * 2. 找到最近最简单方法，将其拆解成可重复解决的问题（重复子问题）
 * 3. 数学归纳法
 *
 *
 *   2
 *  /  \
 * 1    3
 *      \
 *      5
 * https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
 *
 */
public class RecursiveDemo {
    /**
     * 递归代码模板
     * @param args
     */
    /*    public void recur(int level, int param) {
            //1. terminator 递归终结条件
            if (level > Max_LEVEL) {
                //process result
                return;
            }
            //2. process current logic 处理当前逻辑
            process(level, param);
            //3. drill down  下探到下一层
            recur(level: level + 1, newParam);
            //4. restore current level status if needed 清理当前层

        }*/
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(5);


        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode3.setRight(treeNode4);

        //preOrderRecursive(treeNode1);
        //preOrderIterate(treeNode1);

        //inOrderRecursive(treeNode1);
        //inOrderIterate(treeNode1);

        //postOrderRecursive(treeNode1);
        for (Object o : postOrderIterate(treeNode1).toArray()) {
            System.out.println(o);
        }

        levelOrder(treeNode1);
    }

    /**
     * 前序遍历 递归实现
     * 根结点 ---> 左子树 ---> 右子树
     * @param root
     */
    public static void preOrderRecursive(TreeNode root) {
        if (root == null) return;
        System.out.println(root);
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }
    /**
     * 前序遍历  迭代方法
     * 根结点 ---> 左子树 ---> 右子树
     * @param root
     */
    public static void preOrderIterate(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                System.out.println(root+"  ");
                stack.push(root);
                root = root.left;
            } else { //root == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                root = node.right;
            }
        }

    }

    /**
     * 中序遍历 递归实现
     * @param root
     */
    public static void inOrderRecursive(TreeNode root) {
        if (root == null) return;
        inOrderRecursive(root.left);
        System.out.println(root);
        inOrderRecursive(root.right);
    }
    /**
     * 中序遍历  迭代方法
     * @param root
     */
    public static void inOrderIterate(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                System.out.println(node); // Add after all left children 可放到list中
                root = node.right;
            }
        }
    }

    /**
     * 后序遍历 递归
     * @param root
     */
    public static void postOrderRecursive(TreeNode root) {
        if (root == null) return;
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.println(root);
    }
    /**
     * 后序遍历 迭代
     * @param root
     */
    public static List<Integer> postOrderIterate(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                result.addFirst(root.data); //addFirst(Inserts the specified element at the beginning of this list.)  add(Appends the specified element to the end of this list.)
                System.out.println(root);//2 3 5 1
                root = root.right;
            } else {
                TreeNode node = stack.pop();
                root = node.left;
            }
        }
        return result;
    }

    /**
     * 层次遍历 迭代 队列
     *
     * @param root
     */
    public static void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();//Deque  ArrayDeque
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

}
