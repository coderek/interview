import java.io.*;
import java.util.*;

public class Dijkastra2 {
    public static final Long INF = 1000000000;

    public static int getSmallest(Long[] dists) {
        Long a = INF;
        int smallest = -1;
        for (int i = 0; i < n; i++) {
            if (dists[i] <= a) {
                smallest = i;
                a = dists[i];
            }
        }
        return smallest;
    }

    public static Long[] solve(Map<Integer, Map<Integer, Long>> mapping, int n, int start) {
        Set<Integer> vertices = new HashSet<>();
        Long[] dists = new Long[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dists[i] = INF;
            prev[i] = null;
            vertices.add(i);
        }

        dists[start] = 0;

        while (vertices.size() > 0) {
            int smallest = getSmallest(dists);
            vertices.remove(smallest);

            Map<Integer, Long> m = mapping.get(smallest);
            for (Integer i: m.keySet()) {
                Long d = m.get(i) + dists[smallest];

                if (dists[i] > d) {
                    dists[i] = d;
                    prev[i] = smallest;
                }
            }
        }

        for (int i = 0; i< n;i++) {
            if (dists[i] == INF) {
                dists[i] = -1;
            }
        }

        return dists;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
