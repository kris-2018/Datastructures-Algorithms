package com.leetcode.algorithm.bdfs;

import java.util.*;

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
     * 广度优先遍历
     * 两个单词对应位置只有一个字符不同，例如 "hit" 与 "hot"，这种转换是可以逆向的，因此，根据题目给出的单词列表，可以构建出一个无向（无权）图
     * 如果一开始就构建图，每一个单词都需要和除它以外的另外的单词进行比较，复杂度是 O(N wordLen)，这里 N是单词列表的长度；
     * 为此，我们在遍历一开始，把所有的单词列表放进一个哈希表中，然后在遍历的时候构建图，每一次得到在单词列表里可以转换的单词，复杂度是 O(26 x wordLen)，借助哈希表，找到邻居与 N无关；
     * 使用 BFS 进行遍历，需要的辅助数据结构是：
     *      队列；
     *      visited 集合。说明：可以直接在 wordSet (由 wordList 放进集合中得到)里做删除。但更好的做法是新开一个哈希表，遍历过的字符串放进哈希表里。这种做法具有普遍意义。绝大多数在线测评系统和应用场景都不会在意空间开销。
     * 链接：https://leetcode-cn.com/problems/word-ladder/solution/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you-2/
     * 来源：力扣（LeetCode）
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);
        // 图的广度优先遍历，必须使用的队列和表示是否访问过的 visited （数组，哈希表）
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int wordLen = beginWord.length();
        // 包含起点，因此初始化的时候步数为 1
        int step = 1;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                //依次遍历当前队列中的单词
                String word = queue.poll();
                char[] charArray = word.toCharArray();
                //修改每一个字符
                for (int j = 0; j < wordLen; j++) {
                    final char originChar = charArray[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == originChar) {
                            continue;
                        }
                        charArray[j] = k;
                        final String nextWord = String.valueOf(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                return step + 1;
                            }
                            if (!visited.contains(nextWord)) {
                                queue.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    charArray[j] = originChar;
                }
            }
            step++;
        }
        return 0;
    }


    /**
     * two-end BFS  双向广度优先遍历
     * 已知目标顶点的情况下，可以分别从起点和目标顶点（终点）执行广度优先遍历，直到遍历的部分有交集。这种方式搜索的单词数量会更小一些；
     * 更合理的做法是，每次从单词数量小的集合开始扩散；
     * 这里 beginVisited 和 endVisited 交替使用，等价于单向 BFS 里使用队列，每次扩散都要加到总的 visited 里。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength2(String beginWord, String endWord, Set<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        /*分别用左边和
              右边扩散的哈希表代替单向 BFS 里的队列 */
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();


        beginSet.add(beginWord);
        endSet.add(endWord);

        int len = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginSet.size() > endSet.size()) {
                // Swap two sets
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            // temp赋给beginSet后重新创建一个空的
            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();
                //对chs字符固定一位, 进行遍历迭代 如hit固定h | i | t
                for (int i = 0; i < chs.length; i++) {
                    //内层循环 如 hit a-z进行迭代替换h 组成的字符是否包含在wordList中
                    for (char j = 'a'; j <= 'z'; j++) {
                        char old = chs[i];
                        chs[i] = j;
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
            // 将转换后的字符赋给beginSet继续迭代
            beginSet = temp;
            len++;
        }
        return 0;
    }
}
