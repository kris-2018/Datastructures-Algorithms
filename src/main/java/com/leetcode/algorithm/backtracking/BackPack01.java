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

    private int maxW = Integer.MIN_VALUE;// 存储背包中物品总重量的最大值
    public void f(int i, int alputIn, int[] items, int n, int weight) {
        // alputIn 表示当前已经装进去的物品的重量和；i 表示考察到哪个物品了；
        // weight 背包重量；items 表示每个物品的重量；n 表示物品个数
        // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
        // f(0, 0, a, 10, 100)
        if (alputIn == weight || i == n) { // alputIn == weight 表示装满了 ; i==n 表示已经考察完所有的物品
            if (alputIn > maxW)
                maxW = alputIn;
            return;
        }
        f(i+1, alputIn, items, n, weight);
        if (alputIn + items[i] <= weight) { // 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, alputIn + items[i], items, n, weight);
        }
    }
}
