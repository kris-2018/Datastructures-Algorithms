package com.leetcode.datastructure.linkedlist;

/**
 * 哨兵模式
 * 对比下面两段代码，在数组arr 很长的时候，比如几万、几十万，代码二运行得更快点，因为两段代码中执行次数最多就是 while 循环那一部分。
 * 第二段代码中， 通过一个哨兵 arr[n-1] = key，成功省掉了一个比较语句 i<n，不要小看这一条语句，当累积 执行万次、几十万次时，累积的时间就很明显了。
 * 当然，这只是为了举例说明哨兵的作用，
 * 写代码的时候千万不要写第二段那样的代码，因为可 读性太差了。大部分情况下，我们并不需要如此追求极致的性能。
 *
 */
public class SentinelMode {
    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 5, 9, 6};
        System.out.println(find2(arr, 6, 5));

    }

    public static int find(int[] arr, int n, int key) {
        if (arr == null || n <= 0) {
            return -1;
        }
        int i = 0;
        while (i < n) {
            if (arr[i] == key) {
                return i;
            }
            i++;
        }
        return -1;
    }
    public static int find2(int[] arr, int n, int key) {
        if (arr == null || n <= 0) return -1;
        if (arr[n - 1] == key) return n - 1;

        int tmp = arr[n - 1]; // 把 arr[n-1] 的值临时保存在变量 tmp 中，以便之后恢复; 之所以这样做的目的是希望 find() 代码不要改变arr数组中的内容; tmp = 6;
        arr[n - 1] = key; //把 key 的值放到 arr[n-1] 中, 可看做一个哨兵

        int i = 0;
        while (arr[i] != key)
            i++;

        arr[n - 1] = tmp; //恢复 arr[n-1] 原来的值
        if (i == n - 1) { // 如果 i == n-1 说明，在 0...n-2 之间都没有 key，所以返回 -1
            return -1;
        } else {
            return i;
        }
    }

}
