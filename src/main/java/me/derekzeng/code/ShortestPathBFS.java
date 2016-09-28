package me.derekzeng.code;

import java.util.*;

/**
 * Created by derekzeng on 9/27/16.
 */
public class ShortestPathBFS {
    public static void solve(int n, List<List<Integer>> edges, int s) {
        int[] cost = new int[n];
        int[] colors = new int[n];

        for (int i=0;i<n;i++) {
            if (i != s)
                cost[i] = -1;
        }

        Deque<Integer> q = new LinkedList<>();
        q.addLast(s);
        colors[s] = 1;

        while (q.size() > 0) {
            Integer a = q.removeFirst();
            for (Integer b: edges.get(a)) {
                if (colors[b] == 0) {
                    cost[b] = cost[a] + 1;
                    colors[b] = 1;
                    q.addLast(b);
                }
            }
            colors[a] = 2;
        }

        for (int i=0;i<n;i++) {
            if (i == s) continue;
            if (cost[i] == -1) {
                System.out.print("-1 ");
            } else {
                System.out.print(cost[i] * 6 + " ");
            }
        }
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int c = s.nextInt();
        for (int i=0;i<c;i++) {
            int n = s.nextInt();
            int m = s.nextInt();
            List<List<Integer>> edges = new ArrayList<List<Integer>>(n);

            for (int k = 0; k < n; k ++) {
                edges.add(new ArrayList<>());
            }

            for (int j=0;j<m;j++) {
                int a = s.nextInt() - 1;
                int b = s.nextInt() - 1;
                edges.get(a).add(b);
                edges.get(b).add(a);
            }

            int start = s.nextInt();
            solve(n, edges, start - 1);
        }
    }
}
