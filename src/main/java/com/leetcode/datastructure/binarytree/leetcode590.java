package com.leetcode.datastructure.binarytree;

import java.util.*;

/**
 * 590. N叉树的后序遍历
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 * 返回其后序遍历: [5,6,3,2,4,1].
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *
 */
public class leetcode590 {

    /**
     * 迭代法
     * @param root
     * @return
     */
    public List<Integer> postorder(N_aryTree root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<N_aryTree> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            N_aryTree node = stack.pop();
            list.add(node.data);
            for (N_aryTree no : node.children) {
                stack.push(no);
            }
        }
        return list;
    }

    /**
     * 递归调用
     */
    public List<Integer> postorder2(N_aryTree root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        for (N_aryTree node : root.children)
            postorder2(node);
        list.add(root.data);
        return list;

    }
}
