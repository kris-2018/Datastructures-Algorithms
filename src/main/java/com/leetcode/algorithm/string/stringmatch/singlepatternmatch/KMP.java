package com.leetcode.algorithm.string.stringmatch.singlepatternmatch;

/**
 * 字符串模式匹配算法---KMP算法
 * https://www.cnblogs.com/fuck1/p/6059736.html
 * KMP算法可以在O(n+m)的时间数量级上完成串的模式匹配操作，其基本思想是：每当匹配过程中出现字符串比较不等时，不需回溯指针，而是利用已经得到的“部分匹配”结果将模式向右“滑动”尽可能远的一段距离，继续进行比较
 */
public class KMP {
    public static void main(String[] args) {
        String src = "aaaaaaab";
        String sub = "ABCDABDABD";
        int[] next = KMP.getNext(sub);
        //int[] next = lengthKMP(sub.toCharArray());
        for (int i : next) {
            System.out.print(i + "  ");// -1 0 1 2 3
        }
        // System.out.println();
        // System.out.println(KMP.kmp(src, sub));
    }
    //补充上一篇中的对于前缀后缀的讨论的获取部分匹配数组的算法
    public static int[] lengthKMP(char[] mchar) {
        int[] fixNum = new int[mchar.length];
        for (int i = 1, j = 0; i < mchar.length; i++) {
            if (mchar[j] == mchar[i]) {
                fixNum[i] = j + 1;
                j++;
            } else if (j > 0) {
                j = 0;
                i -= j;
            }
        }
        // return [0, 0, 0, 0, 1, 2, 0, 1, 2, 0]ABCDABDABD
        return fixNum;
    }


    // 获取next数组的方法,根据给定的字符串求
    public static int[] getNext(String sub) {

        int j = 1, k = 0;
        int[] next = new int[sub.length()];
        next[0] = -1; // 这个是规定
        next[1] = 0; // 这个也是规定
        //
        while (j < sub.length() - 1) {
            if (sub.charAt(j) == sub.charAt(k)) {
                next[j + 1] = k + 1;
                j++;
                k++;
            } else if (k == 0) {
                next[j + 1] = 0;
                j++;
            } else {
                k = next[k];
            }

        }
        return next;
    }

    // 根据给定的主串和子串，采用KMP算法来获取模式匹配
    public static int kmp(String src, String sub) {

        // 首先生成模式串sub的next[j]
        int[] next = getNext(sub);
        int i = 0, j = 0, index = -1;
        while (i < src.length() && j < sub.length()) {
            if (src.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j];
            }
        }

        // 得到开始匹配的位置索引
        if (j == sub.length()) {
            index = i - sub.length();
        }
        return index;
    }
}
