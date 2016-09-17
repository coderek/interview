package me.derekzeng.code;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DivisibleSumPairs {
    public static int solve(int[] array, int k) {
        int count = 0;

        for (int i = 0; i < array.length; i ++) {
            for (int j = i + 1; j < array.length; j ++) {
                if ((array[i] + array[j]) % k == 0) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        System.out.println(DivisibleSumPairs.solve(a, k));
    }
}

