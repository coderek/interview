package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class IntPalin {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        if (x < 100) return x % 11 == 0;

        int noOfDigits = 0;
        while (Math.pow(10, noOfDigits) <= x) noOfDigits++;
        noOfDigits -= 1;

        int right = 0;
        int left = noOfDigits;
        while (left > right) {
            int r = (x % (int) Math.pow(10, right + 1)) / (int) Math.pow(10, right);
            int l = x / (int) Math.pow(10, left) % 10;
            if (l == r) {
                left --;
                right++;
            } else {
                return false;
            }
        }
        return true;
    }
}
