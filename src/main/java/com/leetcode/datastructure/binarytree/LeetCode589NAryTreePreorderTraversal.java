package com.leetcode.datastructure.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *  https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 *
 */
public class LeetCode589NAryTreePreorderTraversal {

    public static void main(String[] args) {

        List<N_aryTree> children = new ArrayList<>();
        children.add(new N_aryTree(3));
        children.add(new N_aryTree(2));
        children.add(new N_aryTree(4));



        N_aryTree n_aryTree = new N_aryTree(1);
        N_aryTree[] num = {new N_aryTree(3),new N_aryTree(2),new N_aryTree(4)};
        n_aryTree.children = Arrays.asList(num);

        n_aryTree.setChildren(n_aryTree.children);

        System.out.println(n_aryTree.toString());

        //System.out.println(n_aryTree.children.get(2));
        preorder(n_aryTree);

    }


    /**
     * Iterative Solution
     * @param root
     * @return
     */
    public static List<Integer> preorder(N_aryTree root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<N_aryTree> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.data);
            for (int i = root.children.size() - 1; i >= 0; i--) {
                stack.add(root.children.get(i));
            }
        }
        return list;
    }

    /**
     * 递归
     *
     */
    public static List<Integer> list = new ArrayList<>();
    public static List<Integer> preorder2(N_aryTree root) {
        if (root == null) return list;
        list.add(root.data);
        for (N_aryTree node : root.children)
            preorder2(node);
        return list;
    }
}
