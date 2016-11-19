package me.derekzeng.code;

import java.util.*;
import java.io.*;


class QuickSort {

    public static int insertionSortPart2(int[] ar) {
        int count = 0;
        for (int j = 0; j < ar.length; j++) {
            int toInsert = ar[j];
            for (int i = j - 1; i >= -1; i --) {
                if (i == -1) {
                    ar[0] = toInsert;
                    break;
                } else if (ar[i] > toInsert) {
                    ar[i + 1] = ar[i];
                    count ++;
                } else {
                    ar[i + 1] = toInsert;
                    break;
                }
            }
        }
        return count;
    }

    private static int sort(int[] ar, int lo, int hi) {
        if (lo < hi) {
            int[] res = partition(ar, lo, hi);
            int p = res[0];
            int l = res[1];

            int n = sort(ar, lo, p - 1);
            int m = sort(ar, p + 1, hi);
            return n + m + l;
        }
        return 0;
    }

    private static int[] partition(int[] ar, int lo, int hi) {
        int count = 0;
        int pivot = ar[hi];
        int i = lo, t;
        for (int j = lo; j < hi; j ++) {
            if (ar[j] < pivot) {
                t = ar[i];
                ar[i] = ar[j];
                ar[j] = t;
                i += 1;
                count ++;
            }
        }
        t = ar[i];
        ar[i] = pivot;
        ar[hi] = t;
        count ++;

        return new int[]{i, count};
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int[] ar = new int[n];
        for (int i = 0; i < n; i ++) {
            ar[i] = s.nextInt();
        }
        int[] copy = Arrays.copyOf(ar, ar.length);
        int qc = sort(ar, 0, n - 1);
        int ic = insertionSortPart2(copy);

        System.out.println(ic + " " + qc);
    }
}
