package com.leetcode.algorithm.bdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 算法是作用于具体数据结构之上的, 深度优先搜索算法和广度优先搜索算法都是基于 图 这种数据结构的。
 *   这是因为, 图这种数据结构的表达能力很强, 大部分涉及搜索的场景都可以抽象成 图;
 *   图上的搜索算法, 最直接的理解就是, 在图中找出从一个顶点出发, 到另一个顶点的路径。
 * 图有两种主要存储方法: 邻接表和邻接矩阵。下面例子是用邻接表来存储图。
 */
public class Graph {

    private int v; // 顶点(vertex)的个数
    private LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    /* bfs() 函数用于搜索一条从 s 到 t 的最短路径，其中 s 表示起始顶点，t 表示终止顶点。 */
    public void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        final Graph graph = new Graph(10);
        graph.bfs(0, 7);
        int[][] adj = {{0,1,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,1,0}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //System.out.println();
                System.out.print(adj[i][j] + "\t");
            }
        }
        System.out.println();
        System.out.println();


    }
}
