package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class FourSum {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> s1 = new HashMap<>();
        for (int i=0;i<A.length;i++) {
            for (int j=0;j<B.length;j++) {
                s1.put(A[i] + B[j], s1.getOrDefault(A[i] + B[j], 0) + 1);
            }
        }

        int ret = 0;
        for (int i=0;i<C.length;i++) {
            for (int j=0;j<D.length;j++) {
                int remaining = - C[i] - D[j];
                if (s1.containsKey(remaining)) {
                    ret += s1.get(remaining);
                }
            }
        }
        return ret;
    }
}
