package me.derekzeng.code;

import java.util.*;
import java.io.*;


public class BinarySearch {

    private static int solve(int[] ar, int v) {
        int l = 0;
        int r = ar.length - 1;

        while (r > l + 1) {
            int m = (l + r) / 2;

            if (ar[m] > v) {
                r = m;
            } else if (ar[m] < v) {
                l = m;
            } else {
                return m;
            }
        }

        if (ar[r] == v) {
            return r;
        }

        if (ar[l] == v) {
            return l;
        }

        return -1;
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int target = s.nextInt();
        int n = s.nextInt();
        int[] ar = new int[n];

        for (int i = 0; i < n; i ++) {
            ar[i] = s.nextInt();
        }

        int result = solve(ar, target);
        System.out.println(result);
    }
}
