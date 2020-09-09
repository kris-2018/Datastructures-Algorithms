package com.leetcode.algorithm.bdfs.twoendedbfs;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 433. 最小基因变化
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 *
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
 *
 * 注意:
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 *
 * 示例 1:
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 *
 * 返回值: 1
 *
 * 示例 2:
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 *
 * 返回值: 2
 *
 * 示例 3:
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 *
 * 返回值: 3
 *
 *  https://leetcode-cn.com/problems/minimum-genetic-mutation/
 *
 */
public class LeetCode433MinGeneticMutation {
    public static void main(String[] args) {
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println(minMutation(start, end, bank));
        System.out.println(minMutation1(start, end, bank));

    }

    /**
     * BFS
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public static int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;
        Set<String> bankSet = new HashSet<>();

        //将bank数组添加到bankSet转换为字符串类型，去重
        for (String str: bank) bankSet.add(str);
        //charSet 为一个基因库
        char[] charSet = {'A', 'C', 'G', 'T'};
        int level = 0;
        // visited存储访问过的
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                // 取出队列首位元素
                String curr = queue.poll();
                if (curr.equals(end)) return level; //当前基因序列与 目标基因序列相同则返回level
                char[] currArray = curr.toCharArray();
                for (int i = 0; i < currArray.length; i++) {
                    //固定一位i, 遍历charSet
                    char old = currArray[i];
                    for (char c : charSet) {
                        currArray[i] = c;
                        String next = new String(currArray); //将currArray字符类型数组 ==> 字符串类型
                        if (!visited.contains(next) && bankSet.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currArray[i] = old;
                }

            }
            level++;
        }
        return -1;

    }
    /**
     * DFS
     * compare each word in bank with start, and if the difference is just 1, then I'll add that word to set,
     * and move to the next set of words, making that word as start.
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public static int minMutation1(String start, String end, String[] bank) {
        recurse(start, end, bank, 0, new HashSet<String>());
        return count == Integer.MAX_VALUE ? -1 : count;
    }
    static int count = Integer.MAX_VALUE;
    private static void recurse(String start, String end, String[] bank, int soFar, Set<String> visited) {
        if (start.intern() == end.intern()) {
            count = Math.min(count, soFar);
        }
        for (String str : bank) {
            //遍历bank数组, 再遍历bank元素中每个字符
            int diff = 0;
            for (int i = 0; i < str.length(); i++) {
                //判断 start中每个字符 与 str中每个字符是否一致
                if (start.charAt(i) != str.charAt(i)) {
                    diff++;
                    if (diff > 1) break;
                }
            }
            //bank中元素的字符和 起始基因每个字符相差1 diff == 1
            if (diff == 1 && !visited.contains(str)) {
                visited.add(str);
                recurse(str, end, bank, soFar + 1, visited);
                visited.remove(str);
            }
        }
    }

}
