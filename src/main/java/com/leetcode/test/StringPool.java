package com.leetcode.test;

public class StringPool {
    public static void main(String[] args) {
        String str1 = new StringBuilder("58").append("tongcheng").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println();

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());

        int a = 1;
        int ret = 0;
        int res = 0;
        ret = add(3, 5);
        res = a + ret;
        System.out.println(res);

    }

    private static int add(int x, int y) {
        int sum = 0;
        sum = x + y;
        return sum;
    }
}
