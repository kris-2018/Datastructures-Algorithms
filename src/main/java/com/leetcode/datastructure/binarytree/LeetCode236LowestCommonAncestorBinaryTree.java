package com.leetcode.datastructure.binarytree;

/**
 * 236. 二叉树的最近公共祖先   Lowest Common Ancestor of a Binary Tree
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 */
public class LeetCode236LowestCommonAncestorBinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(2);
        TreeNode treeNode6 = new TreeNode(0);
        TreeNode treeNode7 = new TreeNode(8);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(4);


        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode5.setLeft(treeNode8);
        treeNode5.setRight(treeNode9);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);

        /** 3 -> 5 -> 6 -> 2 -> 7 -> 4 -> 1 -> 0 -> 8 */
        System.out.println("原树: " + treeNode1);

        /**  */
        System.out.println("二叉树的最近公共祖先为: " + lowestCommonAncestor(treeNode1, treeNode4, treeNode9));


    }

    /**
     * 递归
     * 最近公共祖先的定义:  设节点 root为节点 p, q,的某公共祖先，若其左子节点 root.left 和右子节点 root.right 都不是 p,q的公共祖先，则称 root是 “最近的公共祖先” 。
     * 若 root是 p, q的 最近公共祖先 ，则只可能为以下情况之一:
     * p和 q 在 root的子树中，且分列 root的 异侧（即分别在左、右子树中）；
     * p = root，且 q在 root 的左或右子树中；
     * q = root ，且 p 在 root的左或右子树中；
     *
     * If p is a node in the tree and q is null, then the LCA of the given nodes, i.e.,
     * the LCA of just p, truly is p itself. Not null.
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //1. terminator 递归进 --满足1.的条件--> 出一层
        if (root == null || root == p || root == q) return root;
        //2. current logic
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) return null; //当left和right同时为空: 说明root的左/右子树中都不包含 p,q, 返回 null;
        if (left == null) return right;//当left为空, right不为空: p,q都不在root的左子树中,直接返回right
        if (right == null) return left;//left不为空,right为空
        return root;// left 和 right 同时不为空 ：说明 p, q分列在 root的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root


/*        if (left == null) //
            return right;
        else if (right == null)
            return left;
        else
            return root;*/

        /*if(left != null && right != null)   return root;
        return left != null ? left : right; */
        //return left == null ? right : right == null ? left : root;
    }
}