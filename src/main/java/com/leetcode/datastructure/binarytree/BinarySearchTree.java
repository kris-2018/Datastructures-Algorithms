package com.leetcode.datastructure.binarytree;

public class BinarySearchTree {
    private TreeNode tree;

    public void insert(int data) {
        if (tree == null) {
            tree = new TreeNode(data);
            return;
        }
        TreeNode p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new TreeNode(data);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new TreeNode(data);
                    return;
                }
                p = p.left;
            }
        }
    }


}
