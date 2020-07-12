package com.leetcode.datastructure.binarytree;

import java.util.HashMap;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * Construct Binary Tree from Preorder and Inorder Traversal
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *      3
 *    /  \
 *   9   20
 *      /  \
 *     15   7
 *
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 *
 */
public class LeetCode105ConstructBinaryTreePreorderAndInorderTraversal {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        System.out.println(buildTree(preorder, inorder).toString());
    }

    /**
     * 递归
     * 先序遍历的顺序是根节点，左子树，右子树。中序遍历的顺序是左子树，根节点，右子树。
     * 所以我们只需要根据先序遍历得到根节点，然后在中序遍历中找到根节点的位置，它的左边就是左子树的节点，右边就是右子树的节点。
     *  preorder = [3,9,20,15,7]
     *  inorder = [9,3,15,20,7]
     * 根结点: 3
     * 左子树: preorder 9
     *        inorder  9
     * 右子树: preorder 20 15 7
     *        inorder  15 20 7
     * 生成左子树和右子树就可以递归的进行了。只需构造左子树和右子树即可，成功把大问题化成了小问题.
     * 用两个指针指向开头和结束位置即可.
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        //return helper(0, 0, inorder.length - 1, preorder, inorder);
        //return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);

        //使用map
        final HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper2(preorder,0, preorder.length, inorder,0, inorder.length, map);

    }

    /**
     * 递归
     * 存在一个问题，在中序遍历中找到根节点的位置每次都得遍历中序遍历的数组去寻找，
     * 我们可以用一个HashMap把中序遍历数组的每个元素的值和下标存起来，这样寻找根节点的位置就可以直接得到了
     *
     * @param preorder
     * @param p_start
     * @param p_end
     * @param inorder
     * @param i_start
     * @param i_end
     * @return
     */
    private static TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        //1. terminator  preorder为空, 返回null
        if (p_start == p_end) return null;
        //2. current logic
        //从前序中找到 根结点
        TreeNode root = new TreeNode(preorder[p_start]);
        //中序遍历中找到根结点, index为根结点
        int index = 0;
        for (int i = i_start; i < i_end; i++) {
            if (root.data == inorder[i]) {
                index = i;
                break;
            }
        }
        //中序中, 根结点的下标
        int leftNum = index - i_start;
        //3. drill down
        //递归的构造左子树
        root.left = buildTreeHelper(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, index); //p_start + leftNum + 1 为左子树的小标,开区间所以 + 1
        //递归构造右子树
        root.right = buildTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, index + 1, i_end);
        return root;
    }

    /**
     * 递归 + 哈希
     * @param preorder
     * @param p_start
     * @param p_end
     * @param inorder
     * @param i_start
     * @param i_end
     * @param map
     * @return
     */
    private static TreeNode buildTreeHelper2(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end, HashMap<Integer, Integer> map) {
        if (p_start == p_end) return null;
        TreeNode root = new TreeNode(preorder[p_start]);
        int i_index = map.get(root.data);

        //drill down
        int leftNum = i_index - i_start;
        root.left = buildTreeHelper2(preorder, p_start + leftNum + 1, p_end, inorder, i_index + 1, i_end, map);
        root.right = buildTreeHelper2(preorder, p_start + leftNum + 1, p_end, inorder, i_index + 1, i_end, map);
        return root;

    }


    private static TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        //先添加根结点
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        //先找到 中序中的根结点
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.data) {
                inIndex = i;
            }
        }
        //inIndex - 1 为左右子树的分界点  递归遍历左子树 两个指针 preStart, index - 1(左子树的边界),
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        //递归遍历右子树,
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }



}
