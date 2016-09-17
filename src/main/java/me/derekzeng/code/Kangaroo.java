package me.derekzeng.code;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Kangaroo {

    public boolean willMeet(int x1, int v1, int x2, int v2) {
        if (x1 < x2 && v1 <= v2) {
            return false;
        }
        if (x1 > x2 && v1 >= v2) {
            return false;
        }
        if (x1 == x2) {
            return true;
        }
        int diffStart = Math.abs(x1 - x2);
        int diffSpeed = Math.abs(v1 - v2);
        return diffStart % diffSpeed == 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();
        Kangaroo k = new Kangaroo();
        System.out.println(k.willMeet(x1, v1, x2, v2));
    }
}

