package com.leetcode.algorithm.string.operatequestion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 进阶：
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 *
 * 1. split, reverse, join
 * 2. reverse整个string, 然后再单独reverse每个单词
 *      如 eulb si yks eht ; 然后再遍历一次(不是嵌套) ,对于里面的单词,把它reverse一遍;
 *      反转2次，巧妙的使用了负负得正的原理。
 *      时间复杂度也是O(n)
 *
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 *
 */
public class LeetCode151ReverseWordsInAString {

    public static void main(String[] args) {
        String s = " the  sky is blue ";
        System.out.println(reverseWords2(s));

    }

    /**  API
     *  split（拆分），reverse（翻转）和 join（连接）等方法，因此我们可以简单的调用内置的 API 完成操作：
     *      使用 split 将字符串按空格分割成字符串数组；
     *      使用 reverse 将字符串数组进行反转；
     *      使用 join 方法将字符串数组拼成一个字符串。
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        //除去开头和末尾的空白字符
        s = s.trim();
        //正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));//反转之后 再join连接但一块
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * 字符串不可变（如 Java 和 Python)，有些语言的字符串可变（如 C++)
     * 对于字符串不可变的语言，首先得把字符串转化成其他可变的数据结构，同时还需要在转化的过程中去除空格。
     * @param s
     */
    public static String reverseWords2(String s) {
        //1. trim the spaces and convert string into array
        StringBuilder sb = trimSpaces(s);
        //2. reverse the whole array
        reverse(sb, 0, sb.length() - 1);
        //3. reverse each word
        reverseEachWord(sb);
        return sb.toString();
    }
    /*3. reverse each word */
    private static void reverseEachWord(StringBuilder sb) {
        int n = sb.length(), start = 0, end = 0;
        while (start < n) {
            //循环至单词末尾, 有空格的位置即停止, 找出end的位置
            while (end < n && sb.charAt(end) != ' ')
                end++;
            //一个个的翻转sb每个中单词
            reverse(sb, start, end - 1);
            start = end + 1;
            end++;
        }
    }
    /*2. reverse the whole sb, 左右指针两两交换  */
    private static void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            //循环遍历, 左右指针 互相交换
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }
    /* 1. trim the spaces and convert string into sb */
    private static StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        //去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ')
            left++;
        //去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ')
            right--;
        // 将字符串间多余的空白字符去除, 单词之间只保留一个空格
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ')
                sb.append(c);
            else if (sb.charAt(sb.length() - 1) != ' ') //判断sb中是否加了空格, 有则不加, 没有则加
                sb.append(c);
            left++;
        }
        return sb;
    }

}
