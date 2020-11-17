package com.leetcode.datastructure.binarytree;

/**
 * 二叉查找树
 * 查找
 * 插入
 * 删除
 */
public class BinarySearchTree {
    public static void main(String[] args) {
        BinarySearchTree searchTree = new BinarySearchTree();
        searchTree.insert(27);
        searchTree.insert(14);
        searchTree.insert(35);
        searchTree.insert(10);
        searchTree.insert(19);
        searchTree.insert(31);
        searchTree.insert(42);
        System.out.println(searchTree.find(27));
    }
    private TreeNode tree;

    /**
     * 二叉查找树的查找操作
     * @param data
     * @return
     */
    public TreeNode find(int data) {
        TreeNode p = tree;
        while (p != null) {
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }
        return null;
    }

    /**
     * 二叉查找树的插入操作
     * @param data
     */
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
            } else { //data < p.data
                if (p.left == null) {
                    p.left = new TreeNode(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 二叉查找树的删除操作
     * @param data
     */
    public void delete(int data) {
        TreeNode p = tree; // p 指向要删除的节点，初始化指向根节点
        TreeNode pp = null; // pp 记录的是 p 的父节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) p = p.right;
            else p = p.left;
        }
        if (p == null) return; //没有找到
        //要删除的节点有两个子节点
        if (p.left != null && p.right != null) { //查找右子树中最小节点
            TreeNode minP = p.right;
            TreeNode minPP = p; // minPP 表示 minP 的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; //将 minP 的数据替换到 p 中
            p = minP; // 下面就变成了删除 minP 了
            pp = minPP;

        }
        // 删除节点是叶子节点或者仅有一个子节点
        TreeNode child; //p 的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child; //删除根结点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }
}
