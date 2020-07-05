package com.leetcode.datastructure.binarytree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例:
 * 你可以将以下二叉树：
 *  1
 * / \
 *2  3
 * / \
 *4  5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 */
public class LeetCode297SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);

        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);

        System.out.println("序列化: " + serialize(treeNode1));
        System.out.println("反序列化: " + deserialize(serialize(treeNode1)));
    }


    // Encodes a tree to a single string. 序列化
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    public static void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null").append(",");
        } else {
            //将当前节点的data添加到sb中, 然后左递归, 右递归。
            sb.append(node.data).append(",");
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }

    // Decodes your encoded data to tree. 反序列化
    public static TreeNode deserialize(String data) {
        Deque<String> deque = new LinkedList<>();
        deque.addAll(Arrays.asList(data.split(",")));
        return buildTree(deque); // 递归调用
    }
    // 2,1,null,null,3,null,null
    public static TreeNode buildTree(Deque<String> deque) {
        String data = deque.remove(); // removes the head of the queue
        if (data.equals("null")) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(data));
            node.left = buildTree(deque);
            node.right = buildTree(deque);
            return node;
        }
    }
}




