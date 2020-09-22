package com.leetcode.algorithm.string.stringmatch.singlepatternmatch;

/**
 * Brute-Force算法，简称为 BF算法，是一种简单朴素的模式匹配算法，常用于在一个主串 S 内查找一个子串 T 的出现位置。
 * BF算法 在主串和字串匹配失败时，主串进行的回溯操作会影响效率，回溯之后，主串与字串有些部分比较是没有必要的。这种简单的丢弃前面的匹配信息是 BF算法 之所以效率低效的一个重要因素。
 *
 */
public class BF {
    public static void main(String[] args) {
        String a = "accbbaaaccssdd";
        String b = "acc";
        System.out.println(bfFind(a, b, 1));
    }

    public static int bfFind(String S, String T, int pos) {
        /*char[] arr1 = S.toCharArray();
        char[] arr2 = T.toCharArray();
        int i = pos;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1; //针回退，并且+1从下一个开始继续匹配
                j = 0;
            }
        }
        if (j == arr2.length) return i - j;
        else return -1;*/

        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int i = pos;
        int j = 1;
        while (i <= s[0] && j <= t[0]) {
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                i = i - j + 2;
                j = 1;
            }
        }
        if (j > t[0]) {
            return i - t[0];
        } else return 0;
    }
}
