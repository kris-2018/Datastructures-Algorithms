package com.leetcode.algorithm.bdfs;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22GenerateParentheses {

    private static List<String> result;
    public static List<String> generateParenthesis(int n) {
        result = new ArrayList<String>();
        generate(0, 0, n, "");
        return result;
    }

    private static void generate(int left, int right, int n, String s) {
        //terminator
        if (left == n && right == n) {
            //filter out the invalid parentheses;
            result.add(s);
            return;
        }
        //process
        //drill down
        if (left < n)
            generate(left + 1, right, n, s + "(");
        if (right < n && right < left) //剪枝
            generate(left, right + 1, n, s + ")");
        //restore
    }
}
