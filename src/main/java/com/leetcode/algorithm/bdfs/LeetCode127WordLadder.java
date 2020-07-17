package com.leetcode.algorithm.bdfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 127. 单词接龙 word-ladder
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *  每次转换只能改变一个字母。
 *  转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 *  https://leetcode-cn.com/problems/word-ladder/description/
 *
 */
public class LeetCode127WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        Set<String> wordList = new HashSet<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        for (String str : wordList) {
            System.out.print(str + "\t");
        }
        System.out.println();
        System.out.println(ladderLength(beginWord, endWord, wordList));

    }

    /**
     * two-end BFS
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        /*分别用左边和
              右边扩散的哈希表代替单向 BFS 里的队列 */
        Set<String> beginSet = new HashSet<>(),
                      endSet = new HashSet<>();

        //int strLen = beginWord.length();
        // visited
        HashSet<String> visited = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int len = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            //保证 beginVisited 是相对较小的集合
            // temp在扩散完成以后, 会成为新的 beginSet
            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);
                        if (endSet.contains(target)) {
                            return len + 1;
                        }
                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        // 恢复，下次再用
                        chs[i] = old;
                    }
                }
            }
            // 这一行代表表示从 begin 这一侧向外扩散了一层
            beginSet = temp;
            len++;
        }
        return 0;
    }
}
