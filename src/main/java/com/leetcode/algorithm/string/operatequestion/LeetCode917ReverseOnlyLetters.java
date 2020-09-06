package com.leetcode.algorithm.string.operatequestion;

import java.util.Stack;

/**
 * 917. 仅仅反转字母
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 *
 * 示例 1：
 * 输入："ab-cd"
 * 输出："dc-ba"
 *
 * 示例 2：
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 *
 * 示例 3：
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *
 *
 * 提示：
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122
 * S 中不包含 \ or "
 * https://leetcode-cn.com/problems/reverse-only-letters/
 *
 */
public class LeetCode917ReverseOnlyLetters {
    public static void main(String[] args) {
        String S = "a-bC-dEf-ghIj";
        System.out.println(reverseOnlyLetters3(S));
    }

    /**
     * Time Complexity:
     *      Time O(N)
     * Use if clause inside while loop
     * @param S
     * @return
     */
    public static String reverseOnlyLetters(String S) {
        StringBuilder sb = new StringBuilder(S);
        for (int i = 0, j = S.length() - 1; i < j;) {
            if (!Character.isLetter(sb.charAt(i))) ++i;
            else if (!Character.isLetter(sb.charAt(j))) --j;
            else {
                sb.setCharAt(i, S.charAt(j));
                sb.setCharAt(j--, S.charAt(i++));
            }
        }
        return sb.toString();
    }

    /**
     * Use while clause inside while loop
     * Time Complexity: Time O(N)
     * @param S
     * @return
     */
    public static String reverseOnlyLetters2(String S) {
        StringBuilder sb = new StringBuilder(S);
        for (int i = 0, j = S.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetter(sb.charAt(i))) i++;
            while (i < j && !Character.isLetter(sb.charAt(j))) j--;
            sb.setCharAt(i, S.charAt(j));
            sb.setCharAt(j, S.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 字母栈
     * 将 S 中的所有字母单独存入栈中，所以出栈等价于对字母反序操作。（或者，可以用数组存储字母并反序数组。）
     * 然后，遍历 S 的所有字符，如果是字母我们就选择栈顶元素输出。
     * @param S
     * @return
     */
    public static String reverseOnlyLetters3(String S) {
        Stack<Character> letters = new Stack<>();
        //将所有字母存入栈
        for (char s : S.toCharArray()) {
            if (Character.isLetter(s))
                letters.push(s);
        }
        StringBuilder result = new StringBuilder();
        //出栈 -- 字母的反转
        for (char s : S.toCharArray()) {
            if (Character.isLetter(s))
                result.append(letters.pop());
            else
                result.append(s);
        }
        return result.toString();
    }

}
