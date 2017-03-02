package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class Hamming {

    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int c=0;
        for (int i = 0; i < 32; i++) {
            c += 1 & ( z >> i);
        }
        return c;
    }
}
