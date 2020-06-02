package com.leetcode.array;

public class leetcode11 {

    public static void main(String[] args) {
        leetcode11 leetcode11 = new leetcode11();
        int[] nums = {0,2,5,7,8,9};
        System.out.println(leetcode11.maxArea2(nums));
    }

    public int maxArea(int[] a){
            int max = 0;
            for (int i = 0;i < a.length - 1; i++){
                for (int j = i + 1; j < a.length; j++){
                    int area = (j - i) * Math.min(a[i], a[j]);
                    max = Math.max(max, area);
                }
            }

            return max;
    }

    public int maxArea2(int[] a){
        int max = 0;
        for (int i = 0,j = a.length - 1;i < j;){
            int minHeight = a[i] < a[j] ? a[i++] : a[j--]; //谁的高度小谁移动
            int area = (j - i + 1) * minHeight;
            max = Math.max(max, area);
        }
        return max;
    }

}
