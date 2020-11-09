package com.leetcode.datastructure.stack;

import java.util.*;

/**
 * 20. 有效的括号  Valid Parentheses
 * 给定一个只包括 '(' ，')' ，'{' ，'}' ，'[' ，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。  注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 *
 * String大、中、小括号 ==> 合法？
 * ① "()"
 * ② "()[]"
 * ③ "([)]" --不合法
 * ④ "((([])))"
 * ⑤ "]][[" --不合法
 *
 *
 *  a. 左 ==> push
 *  b. 右 ==> peek
 *    pop
 *  c. stack empty
 * 时间复杂度是: O(1) * n = O(n)
 *
 * https://leetcode-cn.com/problems/valid-parentheses/solution/
 */

public class LeetCode20ValidParentheses {
    public static void main(String[] args) {
        String str = "{()[]}";
        for (char c: str.toCharArray()){
            System.out.print(c + "\t");
        }
        System.out.println("\n判断是否是有效括号: ");
        System.out.println(isValid(str));
    }

    /**
     * 对应的 Unicode 码为:  ( --> 40, ) --> 41, [ --> 91, ] --> 93, { --> 123, } --> 125
     * char is a primitive type that represents a single 16 bit Unicode character
     * while Character is a wrapper class that allows us to use char primitive concept in OOP-kind of way.
     * 时间复杂度 O(n)  空间复杂度 O (n)
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }

    /**
     * 使用哈希表 和 栈
     * 时间复杂度 O(n)  空间复杂度 O(n)
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        Map<Character, Character> hashMap = new HashMap<>();
        hashMap.put('[', ']');
        hashMap.put('{', '}');
        hashMap.put('(', ')');
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char cha = s.charAt(i);
            if (cha == '[' || cha == '{' || cha =='(') stack.push(cha);
            else if (stack.isEmpty() || (cha != hashMap.get(stack.pop()))) return false;
        }
        return stack.isEmpty();
    }


}
