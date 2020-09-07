package com.leetcode.algorithm.string.basequestion;

import java.util.HashSet;
import java.util.Set;

/**
 * 771. 宝石与石头难度简单554收藏分享切换为英文关注反馈 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 示例 1:
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 *
 * 示例 2:
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 *
 * 注意:
 * 	S 和 J 最多含有50个字母。
 * 	 J 中的字符不重复。
 *  https://leetcode-cn.com/problems/jewels-and-stones/
 *
 */
public class LeetCode771JewelsAndStones {

    public static void main(String[] args) {
        String J = "aA";
        String S = "aAAbbbb";
        System.out.println(numJewelsInStones(J, S));
    }

    /**
     * time complexity O(J+S)
     * pace complexity O(J)
     * @param J read J and build jewels hash set.
     * @param S read S and count jewels.
     * @return
     */
    public static int numJewelsInStones(String J, String S) {
        int res = 0;
        Set<Object> setJ = new HashSet<>();
        for (char j : J.toCharArray())
            setJ.add(j);
        for (char s : S.toCharArray())
            if (setJ.contains(s))
                res++;
        return res;

        /*int count = 0;
        for (char s : S.toCharArray()) {
            if (J.contains(s + ""))
                count++;
        }
        return count;*/
    }

    /**
     *
     * @param J
     * @param S
     * @return
     */
    public static int numJewelsInStones2(String J, String S) {
        return S.replaceAll("[^" + J + "]", "").length();
    }


}
