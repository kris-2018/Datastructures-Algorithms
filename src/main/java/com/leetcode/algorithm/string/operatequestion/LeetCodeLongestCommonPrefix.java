package com.leetcode.algorithm.string.operatequestion;

/**
 * 1. 纯暴力: 直接枚举，从最小的单词来看，看它在其他单词是不是前缀，然后它的单词符号长度再减1，看在这里面是不是前缀...
 *              时间复杂度O(n^2 * m) m为单词的平均长度
 * 2. 行列遍历
 * flower
 * flow
 * flight
 * 把这些字符串对齐排在一起，然后检查第一列是否相同，如果相同看第二列，如果出现有一列不相同了停止，把前边的输出即可。
 * 3. Trie，把所有单词都进trie中
 * 4. 分治的时间复杂度也是比较高的
 * https://leetcode-cn.com/problems/longest-common-preﬁx/ description/
 *
 */
public class LeetCodeLongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));

    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        //i是遍历这个单词它的列
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            //j是遍历所有的单词
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) //看在第i位的单词是否是要对比的标兵字符，只要不相同了公共前缀就没了，前面已经遍历到的就是它的公共前缀，否则单词本身就是它的公共前缀
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}
