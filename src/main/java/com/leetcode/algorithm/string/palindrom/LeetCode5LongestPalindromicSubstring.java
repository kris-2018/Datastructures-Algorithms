package com.leetcode.algorithm.string.palindrom;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * 1. 嵌套循环，枚举i和j（分别代表起点和终点），把所有的子串都找出来，然后再判断该字串是否是回文串 时间复杂度O(n^3)
 * 2. 中间向两边扩张法 O(n^2), 在暴力枚举时, 如果起点和终点不一样肯定就不是回文串了, 如果它是回文串那么它的起点和终点肯定是一样的;
 *      与其枚举它的起点和终点,不如枚举它的中心, 向外扩张时要保证它的左边和右边是相同的。
 *
 *
 *
 *
 */
public class LeetCode5LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindrome(s));
    }


    /**
     * 枚举中心
     * 时间复杂度 O(n^2)
     * @param s
     * @return
     */
    private static int lo, maxLen;
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i);//odd length 奇数, 枚举它的中心(在一个元素上)
            extendPalindrome(s, i, i + 1); //even length 偶数, 枚举它的中心(在两个元素中间间隔的地方)
        }
        return s.substring(lo, lo + maxLen);
    }

    private static void extendPalindrome(String s, int j, int k) {
        //向两边进行扩展, j向左扩展, k向右扩展, 只要i和j上的字符是一样的, 就不断的走; 再看它走过的长度
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    /**
     * 动态规划
     *  i,j 分别代表它的起点和 终点在哪里
     * 首先定义 P(i, j):
     *
     *              true    s[i, j] 是回文串
     *   P(i, j)  =
     *              false   s[i, j] 不是a回文串
     *
     *  接下来
     *  P(i, j) = (P(i+1, j-1) && S[i] == S[j])
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        //两层循环, i是枚举起点, 枚举回文串的起点, 从后往前走
                  //j是枚举它的终点, 回文子串后面的位置, j是从i开始 往i的后面不断累计
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]); //s.charAt(i) == s.charAt(j)说明可以扩张出去了; dp[i + 1][j - 1]说明子串是回文串; j - i < 2 说明长度是0空串 或者为1的串
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}
