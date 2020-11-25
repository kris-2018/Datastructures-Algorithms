package com.leetcode.datastructure.binarytree;

import java.util.*;

/**
 *
 * 二叉树的前中后 层序遍历
 * 递归方法
 * 迭代方法
 * 每个节点最多会被访问两次，所以遍历 操作的时间复杂度，跟节点的个数 n 成正比，也就是说
 * 二叉树遍历的时间复杂度是 O(n)。
 *    2
 *  /  \
 * 1    3
 *      \
 *      5
 *
 * 前序遍历(根左右): 2 1 3 5
 * 中序遍历(左根右): 1 2 3 5
 * 后序遍历(左右根): 1 5 3 2
 * 层序遍历: 2 1 3 5
 * https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
 *
 */
public class BinaryTreeTraversal {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(5);


        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode3.setRight(treeNode4);

        //System.out.println("前序遍历:");
        //preOrderRecursive(treeNode1);
        //preOrderIterate(treeNode1);

        //System.out.println("中序遍历: ");
        //inOrderRecursive(treeNode1);
        //inOrderIterate(treeNode1);

        //System.out.println("后序遍历: ");
        //postOrderRecursive(treeNode1);
/*        for (Object o : postOrderIterate(treeNode1).toArray()) {
            System.out.print(o + "\t");
        }*/


/*        System.out.println("层次遍历：");
        levelOrderRecursive(treeNode1, 0);
        System.out.println("");
        for (Object o : levelOrderRecursive2(treeNode1).toArray()) {
            System.out.print(o + "\t");
        }*/

        //levelOrder(treeNode1);
    }

    /**
     * 1.1 前序遍历 递归实现
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
     * 1.2 前序遍历  迭代方法
     * 栈, 先入后出(函数调用)
     * Deque<TreeNode> deque = new LinkedList<>(); 在Java中使用Deque来模拟使用栈
     *
     * 根结点 ---> 左子树 ---> 右子树
     * @param root
     */
    public static void preOrderIterate(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                System.out.println(root+"  ");
                stack.push(root); //addFirst
                root = root.left;
            } else { //root == null && !stack.isEmpty()
                TreeNode node = stack.pop(); //removeFirst
                root = node.right;
            }
        }
    }

    /**
     * 2.1 中序遍历 递归实现
     * @param root
     */
    public static void inOrderRecursive(TreeNode root) {
        if (root == null) return;
        inOrderRecursive(root.left);
        System.out.println(root);
        inOrderRecursive(root.right);
    }
    /**
     * 2.2 中序遍历  迭代方法
     * @param root
     */
    public static void inOrderIterate(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
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
     * 3.1 后序遍历 递归
     * @param root
     */
    public static void postOrderRecursive(TreeNode root) {
        if (root == null) return;
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.println(root);
    }
    /**
     * 3.2 后序遍历 迭代
     * 左子树 --> 右子数 -->  根结点
     * 此题解法是: 根 -> 先遍历右子树, 再遍历左子树, 最后再倒序输出
     * @param root
     */
    public static Deque<Integer> postOrderIterate(TreeNode root) {
        Deque<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                result.addFirst(root.data); //进来一个元素就添加到首位, 会进行倒序排列
                //System.out.println(root);//2 3 5 1
                root = root.right;
            } else {
                TreeNode node = stack.pop();
                root = node.left;
            }
        }
        return result;
    }

    /**
     * 4.1 层次遍历
     *      迭代 队列
     * 先入先出 : 从根节点开始遍历  -->  依次将根 左孩子  右孩子 添加到 队列中
     * @param root
     */
    public static void levelOrderIterate(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();// 创建一个队列  Deque --> Queue
        queue.offer(root); //将元素添加到队尾
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); //从队首获取元素并删除
            System.out.println(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 4.2 层次遍历
     *      递归实现
     *
     * @param root
     * @param height
     */
    public static void levelOrderRecursive(TreeNode root, int height) {
        if (root == null) return;
        System.out.print(root.data + "\t");
        levelOrderRecursive(root.left, height + 1);
        levelOrderRecursive(root.right, height + 1);

    }

    public static List<List<Integer>> levelOrderRecursive2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderHelper(res, root, 0);
        return res;
    }

    private static void levelOrderHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) res.add(new LinkedList<Integer>());
        res.get(height).add(root.data);
        levelOrderHelper(res, root.left, height + 1);
        levelOrderHelper(res, root.right, height + 1);
    }
}