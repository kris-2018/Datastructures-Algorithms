package com.leetcode.datastructure.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {
        /*Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,1);
        for (Integer integer : map.keySet()) {
            System.out.println(integer);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.print("\nkeys: " + entry.getKey());
            System.out.print("\nvalues: " + entry.getValue());

        }*/
        for (int i = 1;i <= 10;i++){
            if (i % 2 == 0){
                break;
                //continue;
            }
            System.out.println(i);
        }

    }
}
