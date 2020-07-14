package com.leetcode.algorithm.recursive.divideconquer;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.LinkedList;
import java.util.List;

/**
 * 17. 电话号码的字母组合  Letter Combinations of a Phone Number
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 *  https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *  https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 */
public class LeetCode17LetterComOfPhoneNum {

    public static void main(String[] args) {
        for (Object o : letterCombinations("").toArray()) {
            System.out.println(o);
        }
        char charAt = "23".charAt(0);
        System.out.println("23".charAt(0) - '0');
    }

    /**
     * with FIFO queue
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations1(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray()) {
                    ans.add(t + s);
                }
            }
        }
        return ans;
    }


    /**
     * A version without first loop, but same time complexity. Both are single queue BFS solutions.:
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while(ans.peek().length()!=digits.length()){
            String remove = ans.remove();
            String map = mapping[digits.charAt(remove.length())-'0'];
            for(char c: map.toCharArray()){
                ans.addLast(remove+c);
            }
        }
        return ans;
    }

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<String>();
        //输入为null 或 长度为 0 ,返回 [] 而不是 [""]
        if (digits == null || digits.length() == 0) return res;
        combination("", digits, 0, res);
        return res;
    }

    private static void combination(String prefix, String digits, int start, List<String> res) {
        //1. terminator
        if (start >= digits.length()) {
            res.add(prefix);
            return;
        }
        int s =  (digits.charAt(start) - '0');
        String letters = KEYS[s];
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, start + 1, res);
        }
    }

}
