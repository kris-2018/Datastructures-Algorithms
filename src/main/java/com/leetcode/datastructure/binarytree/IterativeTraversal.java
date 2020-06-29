package com.leetcode.datastructure.binarytree;


/**
 *
 * https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeTraversal {

    /**
     * 二叉树的中序遍历
     * 94. Binary Tree Inorder Traversal
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     *
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.data);
            root = root.right;
        }
        return list;

    }

    /**
     * 230. Kth Smallest Element in a BST
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
     *
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) break;
            root = root.right;
        }
        return root.data;
    }

    /**
     * 98. Validate Binary Search Tree
     * https://leetcode.com/problems/validate-binary-search-tree/
     */

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.data <= pre.data) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
}
