package com.leetcode.datastructure.trie;

public class TrieNode {
    public char data;
    public TrieNode[] children = new TrieNode[26];
    public boolean isEndingChar = false;
    public boolean isWord;

    public String fail;

    public TrieNode() {}
    public TrieNode(char data) {
        this.data = data;
    }

    String word;
}
