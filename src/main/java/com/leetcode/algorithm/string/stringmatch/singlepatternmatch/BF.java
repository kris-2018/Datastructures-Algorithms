package com.leetcode.algorithm.string.stringmatch.singlepatternmatch;

/**
 * Brute-Force算法，简称为 BF算法，是一种简单朴素的模式匹配算法，常用于在一个主串 S 内查找一个子串 T 的出现位置。
 * BF算法 在主串和字串匹配失败时，主串进行的回溯操作会影响效率，回溯之后，主串与字串有些部分比较是没有必要的。这种简单的丢弃前面的匹配信息是 BF算法 之所以效率低效的一个重要因素。
 */
public class BF {
    public static void main(String[] args) {
        String src = "BBC ABCDAB ABCDABCDABDE";
        String sub = "ABCDABD";
        System.out.println(bfFind(src, sub));
        System.out.println(forceSearch(src, sub));
    }

    /**
     * 1、如果在主串中查找到子串，则称为模式匹配成功，返回模式串的第一个字符在主串中出现的位置。
     * 2、如果在主串中未找到子串，则称为模式匹配失败，返回-1。
     * @param src 主串
     * @param sub 子串
     * @return 返回模式串的第一个字符在主串中出现的位置。
     */
    public static int bfFind(String src, String sub) {
        int i = 0, j = 0, index = -1;
        while (i < src.length() && j < sub.length()) {
            if (src.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
            } else {
                /*该式子的目的是保证i 的值在匹配不成功时不断向后+1,i是一个不断累加的过程;  j其实表示已经成功匹配的字符数，*/
                i = i - j + 1;
                j = 0;
            }
        }
        //判断
        if (j == sub.length()) {
            // 此处表示在index处开始匹配，并且后面完全匹配成功
            index = i - sub.length();
        }
        return index;
    }

    /**
     * 时间复杂度O(mn)
     * @param txt
     * @param pat
     * @return
     */
    public static int forceSearch(String txt, String pat) {
        int M = txt.length();
        int N = pat.length();
        //i 枚举起点本身, 一般是不能加速的
        for (int i = 0; i <= M -N; i++) {
            int j;
            for (j = 0; j < N; j++) {
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == N) {
                return i;
            }
        }
        return -1;
    }
}
