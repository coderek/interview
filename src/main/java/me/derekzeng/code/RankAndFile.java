package me.derekzeng.code;

import java.util.*;


class RankAndFile {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        for (int i = 0; i < n; i ++) {
            int[] count = new int[2501];
            int m = s.nextInt();
            for (int j = 0; j < 2 * m - 1; j ++) {
                for (int k=0;k<m;k++) {
                    int o = s.nextInt();
                    count[o] += 1;
                }
            }
            int[] result = new int[m];
            int k = 0;
            for (int p=0;p<2501;p++) {
                if (count[p] % 2 == 1) {
                    result[k++] = p;
                }
            }
            Arrays.sort(result);
            String resultStr = "";
            for (int j: result) {
                resultStr += (j + " ");
            }
            System.out.format("Case #%s: %s\n", (i+1), resultStr);
        }
    }
}
