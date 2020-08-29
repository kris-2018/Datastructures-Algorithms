package com.leetcode.algorithm.string.basequestion;

/**
 *58. 最后一个单词的长度
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * 如果不存在最后一个单词，请返回 0 。
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 *
 * 示例:
 * 输入: "Hello World"
 * 输出: 5
 *
 * https://leetcode-cn.com/problems/length-of-last-word/
 *
 */
public class LeetCode58LengthOfLastWord {
    public static void main(String[] args) {
        String str = "Hello World";
        System.out.println(lengthOfLastWord(str));
        System.out.println(lengthOfLastWord2(str));
        System.out.println(lengthOfLastWord3(str));
    }

    /**
     * use lastIndexOf
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }

    /**
     * Use split
     * @param s
     * @return
     */
    public static int lengthOfLastWord2(String s) {
        String[] words = s.split(" ");
        if (words.length == 0) return 0;
        else return words[words.length - 1].length();
    }

    /**
     * Use 2 loops. One to locate the last non-space character, one to count.
     * @param s
     * @return
     */
    public static int lengthOfLastWord3(String s) {
        int lenIndex = s.length() - 1;
        int len = 0;

          /*
        can also use while here, resulting in 264ms
        while (lenIndex>=0 && s.charAt(lenIndex)==' ')
            lenIndex--;
         */

        /*
        or use trim - 324ms
        s = s.trim();
        */
        for (int i = lenIndex; i >= 0 && s.charAt(i) == ' '; i--)
            lenIndex--;
        for (int i = lenIndex; i >= 0 && s.charAt(i) != ' '; i--)
            len++;
        return len;
    }
}
