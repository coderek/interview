package me.derekzeng.code;

import java.util.*;
import java.io.*;
import java.lang.Comparable;

public class Dijkstra2 {
    public static final int INF = 100001;

    static class Node implements Comparable<Node> {
        int index;
        int dist = INF;
        boolean dead = false;

        Node(int index) {
            this.index = index;
        }

        Node(Node c) {
            this.index = c.index;
            this.dist = c.dist;
        }

        @Override
        public int compareTo(Node n) {
            if (this.dist > n.dist) {
                return 1;
            } else if (this.dist < n.dist) {
                return -1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            if (this.dist == INF) {
                return "-1 ";
            } else {
                return this.dist + " ";
            }
        }
    }

    public static void solve(int[][] adj, int n, int start) {
        PriorityQueue<Node> q = new PriorityQueue<>(n);

        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i ++) {
            nodes[i] = new Node(i);
            if (i == start) {
                nodes[i].dist = 0;
            }
            q.add(nodes[i]);
        }

        while (q.size() > 0) {
            Node u = q.remove();
            if (u.dead) continue;

            int[] neighbors = adj[u.index];

            for (int i=0;i<n;i++) {
                int w = neighbors[i];
                Node v = nodes[i];

                if (v.dist > u.dist + w) {
                    v.dist = u.dist + w;
                    v.dead = true;
                    // re heapify
                    Node clone = new Node(v);
                    q.add(clone);
                }
            }
        }

        for (Node nn: nodes) {
            if (nn.index != start) 
                System.out.print(nn);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        InputStream in = new Dijkstra2().getClass().getResourceAsStream("/input.dat");
        Scanner s = new Scanner(in);
        int p = s.nextInt();

        for (int i = 0; i < p; i ++) {
            int n = s.nextInt();
            int m = s.nextInt();

            // Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
            int[][] adj = new int[n][n];
            for (int k = 0; k < n; k ++) {
                for (int j = 0; j < n; j ++) {
                    adj[k][j] = INF;
                }
            }

            Long startTime = System.currentTimeMillis();
            System.out.println("Start reading input: " + startTime);
            for (int j = 0; j < m; j ++) {
                int u = s.nextInt() - 1;
                int v = s.nextInt() - 1;
                int w = s.nextInt();

                adj[u][v] = Math.min(adj[u][v], w);
                adj[v][u] = Math.min(adj[v][u], w);
            }

            int start = s.nextInt() - 1;
            Long imt = System.currentTimeMillis();
            Long end = imt - startTime;
            System.out.println("Done reading input: " + end);

            solve(adj, n, start);
            System.out.println("Done solving: " + (System.currentTimeMillis() - imt));
        }
    }
}
