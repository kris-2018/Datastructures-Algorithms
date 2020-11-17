package com.leetcode.datastructure.binarytree;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * 例如，给定一个 3叉树 :
 *
 * 返回其层序遍历:
 *
 * [
 *      [1],
 *      [3,2,4],
 *      [5,6]
 * ]
 *
 *
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 *
 *  https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 */
public class LeetCode429NAryTreeLevelorderTraversal {

    /**
     * 利用队列实现广度优先搜索
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(N_aryTree root) {
        List<List<Integer>> ret = new LinkedList<>();
        if (root == null) return ret;
        Queue<N_aryTree> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> curLevel = new LinkedList<>();
            for (int i = 0; i < curLevel.size(); i++) {
                N_aryTree curr = queue.poll();
                curLevel.add(curr.data);
                for (N_aryTree c : curr.children)
                    queue.offer(c);
            }
            ret.add(curLevel);
        }
        return ret;
    }
}

