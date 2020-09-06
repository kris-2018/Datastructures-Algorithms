package com.leetcode.algorithm.string.operatequestion;

/**
 * 541. 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 *
 * 示例:
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 *
 *
 * 提示：
 * 该字符串只包含小写英文字母。
 * 给定字符串的长度和 k 在 [1, 10000] 范围内。
 *
 *  https://leetcode-cn.com/problems/reverse-string-ii
 */
class LeetCode541ReverseStringII {

    public static void main(String[] args) {
        String s = "abcdefg"; int k = 2;
        System.out.println(reverseStr(s, k));
    }

    /**
     * 暴力法
     * 时间复杂度 O(n)
     * 辅助数组，用来翻转 s 的一半字符。
     * @param s ，O(n) n 为 s 的大小
     * @param k
     * @return
     */
    public static String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int start = 0; start < arr.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, arr.length - 1);
            //swap(arr, i ,j);
            while (i < j) {
                char tmp = arr[i];
                arr[i++] = arr[j];
                arr[j--] = tmp;
            }
        }
        return String.valueOf(arr);
    }

    public static String reverseStr2(String s, int k) {
        char[] ca = s.toCharArray();
        for (int left = 0; left < ca.length; left += 2 * k) {
            for (int i = left, j = Math.min(left + k - 1, ca.length - 1); i < j; i++, j--) {
                char tmp = ca[i];
                ca[i] = ca[j];
                ca[j] = tmp;
            }
        }
        return new String(ca);
    }
}
