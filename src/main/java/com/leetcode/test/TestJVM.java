package com.leetcode.test;

import java.util.Random;

public class TestJVM {
    public static void main(String[] args) {
/*
        long maxMemory = Runtime.getRuntime().maxMemory();//java虚拟机试图使用最大内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("MAX_MEMORY = " + maxMemory + "字节" + " = " + maxMemory / (double)1024/1024/1024 + " G");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "字节" + " = " + totalMemory /(double)1024/1024/1024 + " G");
*/

        String str = "www.baidu.com";
        while (true) {
            str += str + new Random().nextInt(888888) + new Random().nextInt(999999);
        }
    }
}
