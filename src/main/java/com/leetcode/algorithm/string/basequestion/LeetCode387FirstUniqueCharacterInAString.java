package com.leetcode.algorithm.string.basequestion;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 387. 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 示例：
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 * 提示：你可以假定该字符串只包含小写字母。
 *
 *  https://leetcode.com/problems/first-unique-character-in-a-string
 *
 */
public class LeetCode387FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        String str = "loveleetcode";
        System.out.println(firstUniqChar(str));
        System.out.println(firstUniqChar2(str));

    }

    /**
     *  It takes O(n) and goes through the string twice:
     *      Get the frequency of each character.
     *      Get the first character that has a frequency of one.
     * @param str
     * @return
     */
    public static int firstUniqChar(String str) {
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;
        for (int j = 0; j < str.length(); j++)
            if (freq[str.charAt(j) - 'a'] == 1)
                return j;
        return -1;
    }

    /**
     * LinkedHashMap will not be the fastest answer for this question because the input characters are just from 'a' to 'z',
     * but in other situations it might be faster than two pass solutions. I post this just for inspiration.
     *
     * @param str
     * @return
     */
    public static int firstUniqChar2(String str) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                if(map.get(str.charAt(i)) != null)
                    map.remove(str.charAt(i));
            } else {
                map.put(str.charAt(i), i);
                set.add(str.charAt(i));
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }
}
