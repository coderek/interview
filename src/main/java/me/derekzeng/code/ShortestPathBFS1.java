package me.derekzeng.code;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ShortestPathBFS1 {
    static final int INF = 1000000;
    
    static void solve(int n, int s, List<List<Integer>> adj) {
        int[] dist = new int[n];
        int[] color = new int[n];
        for (int i=0;i<n;i++) {
            dist[i] = INF;
        }
        dist[s] = 0;
        
        LinkedList<Integer> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        q.addLast(s);
        set.add(s);
        
        while (q.size() > 0) {
            Integer t = q.removeFirst();
            set.remove(t);

            color[t] = 1;
            
            int count = 0;
            for (Integer neighbor: adj.get(t)) {
                if (color[neighbor] != 0) continue;
                if (set.contains(neighbor)) continue;
                
                q.addLast(neighbor);
                set.add(neighbor);

                count ++;
                
                if (dist[t] + 1 < dist[neighbor]) {
                    dist[neighbor] = dist[t] + 1;
                }
            }
        }
        
        for (int i=0;i<n;i++) {
            if (i == s) continue;
            int d = dist[i]==INF? -1: dist[i] * 6;
            System.out.print(d + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        InputStream in = new ShortestPathBFS1().getClass().getResourceAsStream("/input.dat");
        Scanner s = new Scanner(in);
        int q = s.nextInt();
        for (int i=0;i<q;i++) {
            System.out.println("Test case " + (i + 1));
            int n = s.nextInt();
            int m = s.nextInt();
            List<List<Integer>> adj = new ArrayList<List<Integer>>();
            for (int k=0;k<n;k++) {
                adj.add(new ArrayList<Integer>());
            }
            
            for (int j=0;j<m;j++) {
                int u = s.nextInt() - 1;
                int v = s.nextInt() - 1;
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            int start = s.nextInt() - 1;
            solve(n, start, adj);
        }
    }
}
