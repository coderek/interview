package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class PartitionPartition {
    List<Integer> ret;
    boolean[][] table;
    String s;

    public int partition(String _s) {
        s = _s;
        ret = new ArrayList<>();
        if (s.isEmpty()) return 0;

        table = new boolean[s.length()+1][s.length()+1];

        for (int size=1;size<s.length();size++) {
            for (int start=0;start+size<s.length()+1;start++) {
                if (size == 1) table[start][start+size] = true;
                else if (size == 2) table[start][start+size] = s.charAt(start) == s.charAt(start+size-1);
                else if (table[start+1][start+size-1] && s.charAt(start) == s.charAt(start+size-1)) {
                    table[start][start+size] = true;
                }
            }
        }

        return backtrace(0, s.length());
    }

    int backtrace(int start, int end) {
        if (table[start][end]) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        for (int i=end;i>start;i--) {
            if (table[start][i]) {
                min = Math.min(min, backtrace(i, end)+1);
            }
        }
        return min;
    }
}
