package com.leetcode.algorithm.string.stringmatch.singlepatternmatch;

/**
 * BM 算法
 */
public class BM {

    public static void main(String[] args) {
        BM bm = new BM();
        char[] a = {'a', 'd', 'c', 'a', 'b', 'c'};
        int n = 6;

        char[] b = {'c', 'b', 'c'};
        int m = 3;

        int[] bc = new int[256];

        bm.generateBC(b, m, bc);
        System.out.println(bm.bm2(a, n, b, m));
    }

    private static final int SIZE = 256;  // 全局变量或成员变量

    /**
     * 将模式串中的每个字符及其下标都存到散列表中。这样就可以快速找到坏字符在模式串的位置下标了
     * @param b 模式串
     * @param m 模式串的长度
     * @param bc 散列表, 只实现一种简单的情况, 假设字符串的字符集不是很大, 每个字符长度是1字节,用大小为256的数组来记录每个字符在模式串中出现的位置。
     *           数组的下标对应字符的 ASCII 码值, 数组中存储这个字符在模式串中出现的位置.
     *        散列表下标为 模式串的字符所对应的ASCII数值,
     *        散列表的value值为 模式串的下标;
     */
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;  // 初始化 bc即散列表
        }
        for (int i = 0; i < m; i++) {
            int ascii = (int) b[i]; // 计算 b[i] 的 ASCII 值
            bc[ascii] = i;
        }
    }

    /**
     * 暴力解法, 框架的搭建
     * @param a 主串
     * @param n 主串的长度
     * @param b 模式串
     * @param m 模式串的长度
     * @return
     */
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE];
        generateBC(b, m, bc);
        //i, j,双指针(头指针和 尾指针)
        int i = 0;
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; j--) {
                if (a[i+j] != b[j])
                    break;
            }
            if (j < 0) {
                return i;
            }
            i = i + (j - bc[(int) a[i+j]]); //往后移动i 位
        }
        return -1;
    }

    /**
     *  suffix 数组和 prefix 数组的计算过程
     * @param b 模式串
     * @param m 模式串的长度
     * @param suffix suffix 数组的下标k 表示后缀子串的长度, 下标对应的数组值存储的是在模式串中跟好后缀{u}相匹配的子串{u*}的起始下标值
     * @param prefix  prefix数组来记录模式串的后缀子串 是否能匹配模式串的前缀子串
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && b[j] == b[m-1-k]) {
                j--;
                k++;
                suffix[k] = j+1;
            }
            if (j == -1)
                prefix[k] = true;
        }
    }





    // a,b 表示主串和模式串；n，m 表示主串和模式串的长度。
    public int bm2(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0; // j 表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是 j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            int x = j - bc[(int)a[i+j]];
            int y = 0;
            if (j < m-1) { // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // 好后缀长度
        if (suffix[k] != -1) return j - suffix[k] +1;
        for (int r = j+2; r <= m-1; ++r) {
            if (prefix[m-r] == true) {
                return r;
            }
        }
        return m;
    }
}
