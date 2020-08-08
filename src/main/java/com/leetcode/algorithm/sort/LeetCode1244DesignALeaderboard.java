package com.leetcode.algorithm.sort;

import java.util.*;

/**
 * 1244 DesignALeaderboard
 * 设计排行榜
 * 请你帮忙来设计这个 Leaderboard 类，使得它有如下 3 个函数：
 *
 * addScore(playerId, score)：
 * 假如参赛者已经在排行榜上，就给他的当前得分增加 score 点分值并更新排行。
 * 假如该参赛者不在排行榜上，就把他添加到榜单上，并且将分数设置为 score。
 *
 * top(K)：返回前 K 名参赛者的得分总和。
 * reset(playerId)：将指定参赛者的成绩清零。题目保证在调用此函数前，该参赛者已有成绩，并且在榜单上。
 * 初始状态下，排行榜是空的。
 *
 * 示例 1：
 * 输入：
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 *  [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 *
 * 输出：
 *  [null,null,null,null,null,null,73,null,null,null,141]
 *
 * 解释：
 *  Leaderboard leaderboard = new Leaderboard ();
 *  leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 *  leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 *  leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 *  leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 *  leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 *  leaderboard.top(1);           // returns 73;
 *  leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 *  leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 *  leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 *  leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 *
 * 提示：
 * 1 <= playerId, K <= 10000
 * 题目保证 K 小于或等于当前参赛者的数量。
 * 1 <= score <= 100
 * 最多进行 1000 次函数调用。
 *
 *
 * 本题要考虑到两点：
 *   如何存储数据
 *   如何排序数据
 *   考虑到参赛者与得分是一一对应关系，因此使用key-value的存储方式比较合理。在查找排名靠前的参赛者时，我们应先对数据以value的值做降序排列，然后再取出前K位即可
 *
 * https://github.com/luliyucoordinate/Leetcode/blob/master/src/1244-Design-A-Leaderboard/1244.java
 */
public class LeetCode1244DesignALeaderboard {
    public static void main(String[] args) {
        LeetCode1244DesignALeaderboard leaderboard = new LeetCode1244DesignALeaderboard();
        leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3,99);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(2));
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
    }

    Map<Integer, Integer> map = new HashMap();
    public LeetCode1244DesignALeaderboard() {
    }
    public void addScore(int playerId, int score) {
        // 取得该参赛者之前的积分，默认为0
        int s = map.getOrDefault(playerId, 0);
        map.put(playerId, s + score);
    }
    public void reset(int playerId) {
        map.put(playerId, 0);
    }

    /**
     *
     * @param k 获取前k名的和
     * @return
     */
    public int top(int k) {
        // 将map进行倒序排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        //获取前k位的和
        int sum = 0;
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            sum += entry.getValue();
        }
        return sum;
    }
}
