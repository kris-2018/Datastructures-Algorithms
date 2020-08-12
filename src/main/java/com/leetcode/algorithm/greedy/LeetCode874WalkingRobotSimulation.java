package com.leetcode.algorithm.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * 874. 模拟行走机器人
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
 * -2：向左转 90 度
 * -1：向右转 90 度
 * 1 <= x <= 9：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物。
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。
 *
 *
 *
 * 示例 1：
 * 输入: commands = [4,-1,3], obstacles = []
 * 输出: 25
 * 解释: 机器人将会到达 (3, 4)
 *
 * 示例 2：
 * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出: 65
 * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
 *
 *
 * 提示：
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * 答案保证小于 2 ^ 31
 *  https://leetcode-cn.com/problems/walking-robot-simulation/description/
 *
 */
public class LeetCode874WalkingRobotSimulation {

    public static void main(String[] args) {
        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = {{2, 4}};

        //int[] commands = {4, -1, 3};
        //int[][] obstacles = {};

        System.out.println(robotSim(commands, obstacles));

    }

    /**
     * 方法二:
     *  int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
     *  North, d = 0, directions[d] = {0, 1}
     *  East,  d = 1, directions[d] = {1, 0}
     *  South, d = 2, directions[d] = {0, -1}
     *  West,  d = 3, directions[d] = {-1, 0}
     *
     *  d will increase by one when we turn right,
     *  and will decrease by one (or increase by three) when we turn left.
     *
     *  时间超过限制
     * @param commands
     * @param obstacles
     * @return
     */
    public static int robotSim(int[] commands, int[][] obstacles) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Set of obstacles indexes in the format of : obstacle[0] + " " + obstacle[1]
        Set<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] + " " + obstacle[1]);
        }
        int x = 0, y = 0, d = 0, maxDistSquare = 0;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -2) {        // Turns left 向左转 90°
                d = (d + 3) % 4;
            } else if (commands[i] == -1) { // Turns right 向右转 90°
                d = (d + 1) % 4;
            } else {                        // Moves forward commands[i] steps
                int step = 0;
                while (step < commands[i]
                        &&
                        (!set.contains( (x + directions[d][0]) + " " + (y + directions[d][1])) )) { //判断是否遇到障碍物
                    x += directions[d][0];
                    y += directions[d][1];
                    step++;
                }
            }
            maxDistSquare = Math.max(maxDistSquare, x * x + y * y);
        }

        return maxDistSquare;
    }

    /**
     * 方法一:
     *  int dx[] = {0,1,0,-1};
     *  int dy[] = {1,0,-1,0};
     *  dx[], dy[] 要竖着对齐看
     *
     *  dx[0]dy[0]  --> (0, 1) 北    -> d == 0
     *  dx[1]dy[1]  --> (1, 0) 东    -> d == 1
     *  dx[2]dy[2]  --> (0, -1) 南   -> d == 2
     *  dx[3]dy[3]  --> (-1, 0) 西   -> d == 3
     *
     * 当读取到调整方向的指令时，如:
     * "-1"：“向右转90度”，只要当前方向curdire + 1就可以得到右转方向  (curdire + 1) % 4，
     * "-2"：“向左转90度”，只要当前方向curdire + 3 就可以得到左转方向 (curdire + 3) % 4，
     * 因为不管curdire当前是哪个方向，左转都在其左边，在dx数组的定义中顺势针数3个就是其左边，所以就是加3
     *
     * 怎么判断是否遇到了障碍物:
     * 障碍物有多个，所以需要有一个障碍物坐标点集合
     * 机器人每试图走一个位置，就用此位置与障碍物集合列表里的坐标进行比较，看是否刚好是障碍物坐标点
     *      不是，则“真正走到这个点”，更新机器人坐标点(curx,cury)
     *      是障碍物，那么不走下一步，停留在当前，执行下一条命令
     */

}
