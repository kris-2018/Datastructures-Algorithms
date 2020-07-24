package com.leetcode.datastructure.binarytree;

/**
 * 98. 验证二叉搜索树 validate-binary-search-tree
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树, 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * https://leetcode.com/problems/validate-binary-search-tree
 *
 *
 * 示例一: 输入: [3, 1, 5, null,null,2],
 *         输出: true
 * 示例二: 输入: [5,1,4,null,null,3,6]
 *         输出: false
 *
 * ①. In-order(中序遍历即左根右结点, 判断当前结点大于前几结点即可) => array 升序
 * ②. Recursion(递归): Validate(..., min, max) ,传一个min和max出去为了递归查询时;
 *         max <-- validate(node.left)  -- 递归左孩子得到最大值
 *         min <-- validate(node.right)  --遍历右孩子得到最小值
 * 对于左子树找它的最大值, 右子树找它的最小值, 然后判断左边的最大值要 < 根结点, 同时右边最小值要 > 根结点值;
 * 继续递归下去, 直到所有的都满足;
 * 这两种方法的时间复杂度都是O(n), n为结点总数;
 *
 */

public class LeetCode98ValidateBST {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);

        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);


       /* TreeNode treeNode1 = new TreeNode(27);
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
        treeNode5.setRight(treeNode7);*/
        System.out.println(isValidBST(treeNode1));
    }

    /**
     * 方法一: 递归调用
     * 二叉搜索树的特征:
     * 如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值;
     *       若它的右子树不为空，则右子树上所有节点的值均大于它的根节点的值; 它的左右子树也为二叉搜索树。
     *   即
     * 当前节点的值是其左子树的值的上界（最大值）
     * 当前节点的值是其右子树的值的下界（最小值）
     *
     * isValidBST 函数表示考虑以 root 为根的子树，判断子树中所有节点的值是否都在 (minData, maxData) 的范围内
     *   在递归调用左子树时，我们需要把上界改为 root.data(最大值)，即调用 isValidBST(root.left, minData, root.data)，因为左子树里所有节点的值均小于它的根节点的值。
     * 同理递归调用右子树时，我们需要把下界改为 root.data(最小值)，即调用 isValidBST(root.right, root.data, maxData)。
     * 这里的边界值应该是Long, |Long| > |Integer|, 由于TreeNode之前定义的为int类型,此处不做修改。
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        // minVal = -2147483648   maxVal = 2147483647  (minDadta, maxData),传入参数的值最小值和 最大值
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode root, int minData, int maxData) {
        if (root == null) return true;
        /* current logic 判断当前的节点的值是否在边界值内 (minDadta, maxData), 不在即false */
        if (root.data >= maxData || root.data <= minData) return false;
        // 左子树所有值 < 根结点  && 右子树所有值 > 根结点
        return isValidBST(root.left, minData, root.data) && isValidBST(root.right, root.data, maxData);
    }
}
