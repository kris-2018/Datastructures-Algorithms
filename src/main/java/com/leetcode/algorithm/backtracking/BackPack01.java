package com.leetcode.algorithm.backtracking;

/**
 * 背包问题
 *
 * 0-1 背包是非常经典的算法问题，很多场景都可以抽象成这个问题模型。这个问题的经典解法是动态规划，不过还有一种简单但没有那么高效的解法，那就是回溯算法。
 * 0-1 背包问题有很多变体，介绍一种比较基础的。
 * 有一个背包总的承载重量是 Wkg。现在有 n个重量不等的物品，不可分割。选择几件物品，装载到背包中。在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 * 这个背包问题，物品是不可分割的，要么装要么不装，所以叫 0-1 背包问题。显然，这个问题已经无法通过贪心算法来解决了。
 * 对于每个物品来说，都有两种选择，装进背包或者不装进背包。对于 n 个物品来说，总的装法就有 2^n种，去掉总重量超过 Wkg的，从剩下的装法中选择总重量最接近 Wkg的。
 * 把物品依次排列，整个问题就分解为了 n 个阶段，每个阶段对应一个物品怎么选择。先对第一个物品进行处理，选择装进去或者不装进去，然后再递归地处理剩下的物品，发现已经选择的物品的重量超过 ​之后，就停止继续探测剩下的物品。
 *
 */
public class BackPack01 {
    public static void main(String[] args) {
        BackPack01 backPack01 = new BackPack01();

        //回溯算法的测试:
        System.out.println(backPack01.maxW);
        backPack01.f(0, 0);
        System.out.println(backPack01.maxW);

        //动态规划的测试:
        int[] weight = {2, 2, 4, 6, 3};
        int n = 5, w = 16;
        System.out.println(knapsack(weight, n, w));


    }

    /**
     * 回溯-递归-备忘录
     * 满足背包最大重量限制的前提下，求背包中物品总重量的最大值是多少呢
     * 借助递归“备忘录”的解决方式，记录已经计算好的 f(i, cw)，当再次计算到重复的 f(i, cw) 的时候，可以直接从备忘录中取出来用，
     * 就不用再递归计算了，这样就可以避免冗余计算。
     */
    private int maxW = Integer.MIN_VALUE;// 结果放到 maxW 中
    private int[] weight = {2, 2, 4, 6, 3};// 物品重量
    private int n = 5;//物品个数
    private int w = 16; // 背包承受的最大重量
    private boolean[][] mem = new boolean[5][16]; //备忘录，默认值 false
    public void f(int i, int cw) {
        if (cw == w || i == n){  // cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW)
                maxW = cw;
            return;
        }
        if (mem[i][cw]) return; //重复状态
        mem[i][cw] = true; //记录(i, cw) 这个状态
        f(i+1, cw); // 选择不装第 i 个物品
        if (cw + weight[i] <= w) {
            f(i+1, cw + weight[i]); // 选择装第 i 个物品
        }
    }

    /**
     * 回溯算法
     * 满足背包最大重量限制的前提下，求背包中物品总重量的最大值是多少呢
     * 利用回溯算法穷举搜索所有可能的装法，然后找出满足条件的最大值。不过，回溯算法的复杂度比较高，是指数级别的
     * @param i i 表示考察到哪个物品了
     * @param cw 表示当前已经装进去的物品的重量和
     * @param items 表示每个物品的重量
     * @param n 表示物品个数
     * @param w 背包重量
     */
    public void f(int i, int cw, int[] items, int n, int w) {
        // cw 表示当前已经装进去的物品的重量和；i 表示考察到哪个物品了；
        // w 背包重量；items 表示每个物品的重量；n 表示物品个数
        // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
        // f(0, 0, a, 10, 100)
        if (cw == w || i == n) { // cw == w 表示装满了 ; i==n 表示已经考察完所有的物品
            if (cw > maxW)
                maxW = cw;
            return;
        }
        f(i+1, cw, items, n, w);
        if (cw + items[i] <= w) { // 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, cw + items[i], items, n, w);
        }
    }

    /**
     *
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public static int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w+1];//默认值false
        states[0][0] = true; // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][weight[0]] = true;
        for (int i = 1; i < n; i++) { //动态规划状态转移
            for (int j = 0; j <= w; j++) {           // 不把第 i 个物品放入背包
                if (states[i-1][j] == true) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; j++) { // 把第 i 个物品放入背包
                if (states[i-1][j] == true) states[i][j+weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; i--) {  // 输出结果
            if (states[n-1][i] == true) return i;
        }
        return 0;
    }

    /**
     * 对上述代码的优化
     * @param weight
     * @param n
     * @param w
     * @return
     * 内层for循环j必须从大到小来处理，否则会出现 for 循环重复计算的问题。
     */
    public static int knapsack2(int[] weight, int n, int w) {
        boolean[] states = new boolean[w + 1];
        states[0] = true; // 第一行的数据要特殊处理，可以利用哨兵优化
        states[weight[0]] = true;
        for (int i = 1; i < n; i++) { // 动态规划
            for (int j = w - weight[i]; j >= 0; j--) { // 把第 i 个物品放入背包
                if (states[j] == true) states[j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; i--) { //输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }
}
