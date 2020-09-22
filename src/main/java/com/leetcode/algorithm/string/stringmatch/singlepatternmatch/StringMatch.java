package com.leetcode.algorithm.string.stringmatch.singlepatternmatch;

public class StringMatch {
    public static void main(String[] args) {
        String txt = "BBC ABCDAB ABCDABCDABDE";
        String pat = "ABCDABD";
        //System.out.println(forceSearch(txt, pat));
        System.out.println(RabinKarpSearch(txt, pat));
    }

    /**
     * 时间复杂度O(mn)
     * @param txt
     * @param pat
     * @return
     */
    public static int forceSearch(String txt, String pat) {
        int M = txt.length();
        int N = pat.length();
        //i 枚举起点本身, 一般是不能加速的
        for (int i = 0; i <= M -N; i++) {
            int j;
            for (j = 0; j < N; j++) {
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == N) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 优化:
     * 1. Rabin-karp (暴力 + 哈希预判)类似Bloom Filter一样前面加了一个筛子
     * txt长度M, pat长度N;
     * 每次从txt字符串M长度中 抠出N长度的子串, 可以一步加速直接比较txt里边的子串:  预先判断 hash(txt.substring(i, M)) == hash(pat)
     * 如果哈希值是一样的, 再用暴力法继续每一个字符去比较; 如果它的哈希值不一样就不需要挨个去比较。
     * pat的哈希会预先计算出来, txt片段的要进行更新它是类似于一个滑动窗口问题。
     *
     *
     * 2. KMP
     * txt = BBC ABCDAB AB
     * pat =     ABCDABD
     * ABCDAB都能够和待匹配的(ABCDAB)匹配上, 说明前面是完全一样的,在pat ABCDAB中找出它的前缀和后缀 与txt子串最大的重合可能, 发现AB和AB是完全重合的,说明它最大前后缀重复的长度是2
     *  pat中ABCDAB都匹配上了, D还没匹配上, 就把pat往后挪动(6-2)个位置（把前缀往后的依次挪到后缀往后的位置上）
     *
     * KMP就是用来找已经匹配的这一片段, 它的最大前缀和最大的后缀最长有多长 这样子一个概念。
     *
     */


    /**
     * 它这个哈希函数设计的比较巧妙,导致每次i换位置之后, txtHash可以直接用O(1)的办法进行更新, 否则就要调系统自带的对于String的哈希函数,还是O(m)
     * 最坏时间复杂度O(mn) //哈希相同时内嵌的for循环
     * 平均情况是O(n)
     *
     * @param txt
     * @param pat
     * @return
     */
    public final static int D = 256; // 因为一个字符是 0 - 256进制,可以认为是256进制; 字符串的第一位是0-256, 第二位是0-256里面的一个数再乘以256; 每一个字符相当于要乘以256加在一起最后的和就可以进行有效的判重
                                        //每一位它的权重就相当于是256的相应的次方, txt里边第0位即256^0...; 每次都乘256就非常大, 防止爆掉, 每次取模一个素数, 选了Q = 9997这样的素数
    public final static int Q = 9997;
    public static int RabinKarpSearch(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int patHash = 0, txtHash = 0;
        for (i = 0; i < M; i++) {
            patHash = (D * patHash + pat.charAt(i)) % Q; //计算哈希, 把之前哈希的值再乘以256, 再加当前位的ASCII码的值, 加在一起之后再取模一个9997
            txtHash = (D * txtHash + txt.charAt(i)) % Q;
        }
        int highestPow = 1; //pow(256, M-1), 最高位的权重值
        //当txt移动时,前面的值挪走: 把最高位它相应的值减去,再把最低位的值加进来;最高位值得权重即 循环 M - 1次 计算出一个pow 256的M-1次方
        for (i = 0; i <= M - 1; i++)
            highestPow = (highestPow * D) % Q;

        for (i = 0; i <= N - M; i++) {  // 枚举匹配的起点, 枚举txt子串它的起点
            if (patHash == txtHash) {  // 如果两者的哈希值相同就进行朴素的算法(比较txt和pat相应位置上的每个字符是否一样)
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }
                if (j == M) return i;
            }
            //哈希值不相等, 类似滑动窗口问题, 最高位的字符走出去,最低位的字符走进来。
                //把第i位的字符 * highestPow, 从txtHash里面减去, 再乘以256, 因为滑动窗口要挪动一位, 挪完之后再加上新进来的一位, 新进来的这一位是i+M, 这样子就可以更新hash的值
            if (i < N - M) {
                txtHash = (D * (txtHash - txt.charAt(i) * highestPow) + txt.charAt(i + M)) % Q;
                if (txtHash < 0) txtHash += Q; //减有可能是负数, 就给它再加上一个Q
            }
        }
        return -1;
    }
}
