package me.derekzeng.code;


import java.util.*;
import java.io.*;


class FiboModified {
    private static String bigAdd(String a, String b) {
        int zero = (int) '0';
        int[] aa = a.chars().map(c -> c - zero).toArray();
        int[] bb = b.chars().map(c -> c - zero).toArray();

        int l = Math.max(aa.length, bb.length);
        int[] carry = new int[l];

        for (int i = 0; i < l; i ++) {
            System.out.print(aa[i] + " " + bb[i]);
            System.out.println();
        }
        return "";
    }

    private static String bigMultiply(String a, String b) {
        return "";
    }

    private static void solve(String t1, String t2, int n) {
        while (n - 2 > 0) {
            String t = t2;
            t2 = bigAdd(t1, bigMultiply(t2, t2));
            t1 = t;
            n--;
        }
        System.out.println(t2);
    }

    public static void main(String[] args) {
        // Scanner s = new Scanner(System.in);
        // int t1 = s.nextInt();
        // int t2 = s.nextInt();
        // int n = s.nextInt();
        // solve(t1 + "", t2 + "", n + "");
        
        System.out.println(bigAdd("123", "456"));
        
    }
}
