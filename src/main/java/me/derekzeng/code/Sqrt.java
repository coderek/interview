package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class Sqrt {

    public int mySqrt(int x) {
        if (x==1) return 1;
        int e = x;
        int s = 0;
        while (s<e-1) {
            int pick = (s+e)/2;
            // must use division to avoid overflow
            if (pick == x/pick) return pick;
            if (pick<x/pick) {
                s = pick;
            } else {
                e = pick;
            }
        }
        return s;
    }

}
