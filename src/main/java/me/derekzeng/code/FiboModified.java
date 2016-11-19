package me.derekzeng.code;


import java.util.*;
import java.io.*;


class FiboModified {
    private static final int zero = (int) '0';
    private static int[] bigAdd(int[] aa, int[] bb) {
        int l = Math.max(aa.length, bb.length);
        int[] result = new int[l + 1];
        int carry = 0;

        for (int i = 1; i < l + 1; i ++) {
            int m = 0; // a
            int n = 0; // b
            if (aa.length - i >= 0) {
                m = aa[aa.length - i];
            }
            if (bb.length - i >= 0) {
                n = bb[bb.length - i];
            }

            int s = m + n;

            if (carry == 1) {
                s += 1;
            }

            result[result.length - i] = s % 10;

            if (s > 9) {
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if (carry == 1) {
            result[0] = 1;
            return result;
        } else {
            return Arrays.copyOfRange(result, 1, result.length);
        }
    }

    private static String bigAddString(String a, String b) {
        int[] aa = a.chars().map(c -> c - zero).toArray();
        int[] bb = b.chars().map(c -> c - zero).toArray();

        int[] result = bigAdd(aa, bb);

        return interpret(result);
    }

    private static String interpret(int[] result) {
        char[] chs = new char[result.length];
        for (int i = 0; i < result.length; i ++) {
            chs[i] = (char) (result[i] + zero);
        }

            // System.out.println();
            // System.out.println("------");
            // for (char c: chs) {
            //     // System.out.print((char) (c + zero));
            //     System.out.print(c);
            // }
            // System.out.println();
            // System.out.println("------");
        return new String(chs);
    }

    private static String bigMultiply(String a, String b) {
        int[] aa = a.chars().map(c -> c - zero).toArray();
        int[] bb = b.chars().map(c -> c - zero).toArray();


        int[] total = {0};
        for (int i = 1; i < bb.length + 1; i ++) {
            int m = bb[bb.length - i]; // b

            int[] repeatAddingA = {0};
            for (int j = 0; j < m; j ++) {
                repeatAddingA = bigAdd(aa, repeatAddingA);
            }
            int[] grow = new int[repeatAddingA.length + i - 1];
            for (int j = 0; j < repeatAddingA.length; j ++) {
                grow[j] = repeatAddingA[j];
            }
            total = bigAdd(grow, total);
        }
        return interpret(total);
    }

    private static void solve(String t1, String t2, int n) {
        while (n - 2 > 0) {
            String t = t2;
            t2 = bigAddString(t1, bigMultiply(t2, t2));
            t1 = t;
            n--;
        }
        System.out.println(t2);
    }

    public static void main(String[] args) {
        // System.out.println(bigMultiply("15", "6"));
        
        Scanner s = new Scanner(System.in);
        int t1 = s.nextInt();
        int t2 = s.nextInt();
        int n = s.nextInt();
        solve(t1 + "", t2 + "", n);
    }
}
