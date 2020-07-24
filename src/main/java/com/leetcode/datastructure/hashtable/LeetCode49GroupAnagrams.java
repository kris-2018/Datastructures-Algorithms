package com.leetcode.datastructure.hashtable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *  https://leetcode-cn.com/problems/group-anagrams/
 *
 *  char字符  对应的 ASCII 为:
 *  a 97,b 98  --...-- z 122
 *  A 65,B 66  --...-- Z 90
 */
public class LeetCode49GroupAnagrams {
    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
        for (Object o : groupAnagrams3(str).toArray()) {
            System.out.print(o + " ");
        }

    }

    /**
     * 排序数组内元素 分类
     * 当且仅当它们的排序字符串相等时，两个字符串是字母异位词; 将每个字符串按照字母顺序排序，这样的话就可以把 eat，tea，ate 都映射到 aet。其他的类似
     * 时间复杂度为 O(nklogk) 乘法法则: O(n) * klogk , n为str的长度, k为str中元素字符串的最大长度
     * 空间复杂度为 O(nk) 用来存储结果
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca); //将数组中每个元素的字母排序
            String keyStr = String.valueOf(ca); //获取ca数组的 value值作为key valueOf -> contains the characters of the character array.
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }


    /**
     * 不用 Arrays.sort(ca)
     * Use new char[26] as bucket to count the frequency instead of Arrays.sort,
     * this can reduce the O(klogk) to O(k) when calculate the key.
     * 时间复杂度为 O(nk)  空间复杂度为 O(nk)
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ca = new char[26];
            for (char c : str.toCharArray()){ //前边for循环遍历整个strs数组中每个元素, 此for循环遍历每个元素的每个字符
                ca[c - 'a']++; //记录每个字符出现的次数
            }
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 巧用Stream简化代码
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams3(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(x->{
            char[] chars = x.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        })).values());

    }
}
