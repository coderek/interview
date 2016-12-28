package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class Atoi {
    public int myAtoi(String str) {
        if (str.length() == 0) {
            return 0;
        }

        boolean negative = false;

        if (str.charAt(0) == '+') {
            str = str.substring(1);
        } else if (str.charAt(0) == '-') {
            negative = true;
            str = str.substring(1);
        }

        List<Character> numChars = new LinkedList<>();
        for (int i=0;i<str.length();i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                numChars.add((Character) c);
            } else {
                break;
            }
        }
        if (numChars.size() == 0) {
            return 0;
        } else {
            long l = 0;
            int i = 0;
            while (i < numChars.size()) {
                l += Math.pow(10, i) * (numChars.get(numChars.size() - i - 1) - '0');
                i++;
            }
            if (negative) {
                l = -l;
            }
            return (int) Math.min(Math.max(l, Integer.MIN_VALUE), Integer.MAX_VALUE);
        }
    }
}
