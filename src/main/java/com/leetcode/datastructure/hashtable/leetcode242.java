package com.leetcode.datastructure.hashtable;

import java.util.HashMap;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * s 和 t 的字符都相同
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * https://leetcode-cn.com/problems/valid-anagram/
 */
public class leetcode242 {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println("t是否是s的字母异位词: " + isAnagram2(s, t));
    }

    /**
     * 时间复杂度 O(n)  空间复杂度 O(n)
     * 3个循环
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++; //每个字符计正数
        }
        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--; //每个字符计负数
        }
        for (int i : alphabet)
            if (i != 0) { //正负低效即0  --> 异位词
                return false;
            }
        return true;
    }

    /**
     * 对上边方法的改进
     * put an extra conditional statement in the second loop. Average time will be much better as it does not have to go through the 3rd loop every time.
     * With this line, as soon as a discrepancy is found, it will return false and break out of the loops.
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] alphabet = new int[26];
        for (Character c : s.toCharArray()) alphabet[c - 'a']++;
        for (Character c : t.toCharArray()) {
            if (alphabet[c - 'a'] == 0) return false;
            alphabet[c - 'a']--;
        }
        return true;
    }

    /**
     * 使用hash, 计数
     * 时间复杂度 O(n)  空间复杂度 O(n)
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }
        for (char ca : map.keySet()) {
            if (map.get(ca) != 0) return false;
        }
        return true;

    }

}
