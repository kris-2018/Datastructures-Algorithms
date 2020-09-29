package com.leetcode.algorithm.string.stringmatch.multipatternmatch;


public class AcNode {
    public char data;
    public AcNode[] children = new AcNode[26];// 字符集只包含 a~z 这 26 个字符
    public boolean isEndingChar = false; // 结尾字符为 true
    public int length = -1; // 当 isEndingChar=true 时，记录模式串长度
    public AcNode fail; // 失败指针

    public AcNode(char data) {
        this.data = data;
    }


}
