package me.derekzeng.code;

import java.io.*;
import java.util.*;

public class CutSticks {

    public static void solve(int[] sticks) {
        Arrays.sort(sticks);

        int last = -1;
        for (int i = 0; i < sticks.length; i ++) {
            if (last == -1 || sticks[i] != last) {
                last = sticks[i];
                System.out.println(sticks.length - i);
            }

        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] sticks = new int[n];
        for (int i = 0; i < n; i ++) {
            sticks[i] = scanner.nextInt();
        }

        solve(sticks);
    }
}
