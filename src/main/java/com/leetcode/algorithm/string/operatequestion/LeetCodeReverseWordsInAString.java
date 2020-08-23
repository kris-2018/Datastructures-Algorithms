package com.leetcode.algorithm.string.operatequestion;

import java.util.Arrays;
import java.util.Collections;

/**
 * 1. split, reverse, join
 * 2. reverse整个string, 然后再单独reverse每个单词
 *      如 eulb si yks eht ; 然后再遍历一次(不是嵌套) ,对于里面的单词,把它reverse一遍;
 *      反转2次，巧妙的使用了负负得正的原理。
 *      时间复杂度也是O(n)
 *
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 *
 */
public class LeetCodeReverseWordsInAString {

    public static String reverseWords(String s) {
        String[] words = s.trim().split(" + "); //以 + 分割开转为一个数组
        Collections.reverse(Arrays.asList(words)); //反转之后 再join连接但一块
        return String.join(" ", words);
    }
}
