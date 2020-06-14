package com.leetcode.linkedlist;

public class LinkedList {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        for (Object o : linkedList.toArray()) {
            System.out.println(o);
        }

    }

    private Node first; //第一个节点的地址,节点就包含了数据data和next节点;
    private int total;

    class Node{
        Object data;
        Node next;
        public Node(Object data, Node next) {
            super();
            this.data = data;
            this.next = next;
        }
    }
    //1、添加方法
    public void add(Object obj){
        Node newNode = new Node(obj, null); //1)创建新节点newNode,它就包含数据对象和记录下一节点的地址
        if(first == null){  //2)如果链表是空的;
            first = newNode; //
        }else{
            Node last = first;// 1）先找到最后一个节点的地址
            while(last.next != null){
                last = last.next;
            }  //如果为空了循环就退出来了,就把新节点赋值给它
            last.next = newNode; //2）然后最后一个节点.next = newNode;
        }
        total++;
    }
    //2、获取有效元素个数
    public int size(){
        return total;
    }

    //返回链表所有的数据
    public Object[] toArray(){
        Object[] all = new Object[total];
        //遍历链表,把Node的data放到all[i]中;

        Node node = first; //
        for (int i = 0; i < total; i++) {
            all[i] = node.data;
            node = node.next;
        }
        return all;

    }

}
