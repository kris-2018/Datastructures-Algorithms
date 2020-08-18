package com.leetcode.datastructure.linkedlist;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *  回文串是字符串两边的字符相同, 一一对应.
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 * 输入: "race ae car"
 * 输出: true    //左race  a  右ecar
 *
 *  https://leetcode-cn.com/problems/valid-palindrome/
 *
 */
public class LeetCode125ValidPalindrome {
    public static void main(String[] args) {
        String str = "race ae car";
        System.out.println(isPaindrome2(str));
    }

    /**
     * 双指针
     * 时间复杂度 O(n), 空间复杂度 O(1)
     * @param str
     * @return
     */
    public static boolean isPaindrome(String str) {
        char[] c = str.toCharArray();
        for (int i = 0, j = c.length - 1; i < j; ) {
            if (!Character.isLetterOrDigit(c[i]))
                i++;
            else if (!Character.isLetterOrDigit(c[j]))
                j--;
            else if (Character.toLowerCase(c[i++]) != Character.toLowerCase(c[j--])) //判断 左右两边字符是否相同
                return false;
        }
        return true;
    }

    /**
     * 正则匹配
     * @param str
     * @return
     */
    public static boolean isPaindrome2(String str) {
        String s = str.replaceAll("[^A-Za-z0-9]", "");
        String revs = new StringBuilder(s).reverse().toString();
        return s.equals(revs);
    }

}
