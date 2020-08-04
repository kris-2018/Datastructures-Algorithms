package com.leetcode.datastructure.binarytree;

/**
 *
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 *  https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 *
 *
 *  235. 236: Lowest Common  Ancestor
 * 题目: 二叉树/二叉搜索树的最近公共祖先
 * 示例一:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 *
 * 示例二:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 *
 * ① Path,Cparent-指针
 * path1
 *       ===> LCA
 * path2
 * 时间复杂度O(N)
 * ② Recusion
 *     findPorQ(root, P, Q) -- 辅助函数, root表当前结点, P, Q是两个目标的结点; 表以root为根的树中,如果能找到P就返回P, 如果找到Q就返回Q数据, 如果都能找到任意返回一个即可;
 *        if root == P or Q: return root
 *         else findPorQ(root left P, Q) 分别对左右子树调递归的函数
 *              findPorQ(... right, P, Q)
 * 假设从树最顶端结点开始找，假设根结点刚好等于P或者Q, 说明根结点就是它们的公共祖先
 * 假设树顶端结点既不是P也不是Q，那么它们一定分布在左右子树中,
 * 假设从左子树开始找,如果左子树中存在P或者Q且右子树中也存在P或Q, 则公共祖先就是顶端root结点, 不然左子树中不存在;
 *    <=或者这样思考=>如果左子树中存在P或Q, 则右子树返回的结果就是空了,这时只需要找左子树
 * 时间复杂度O(N)
 *
 */
public class LeetCode235LowestCommonAncestorOfABST {
}
