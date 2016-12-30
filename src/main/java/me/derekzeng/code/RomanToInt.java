package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class RomanToInt {
    public int romanToInt(String s) {
        /*
         * I, V, X, L, C, D, M
         * 1, 5, 10, 50, 100, 500, 1000
         *
         * Subtract I, X, C
         */
        Map<Character, Integer> map=new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int i=0;
        int ret=0;
        while (i< s.length()) {
            char c = s.charAt(i);
            int current=map.get(c);
            i++;

            if (i<s.length()) {
                if (c == 'I') {
                    if (s.charAt(i) == 'V') {
                        current = map.get('V') - current;
                        i++;
                    } else if (s.charAt(i) == 'X') {
                        current = map.get('X') - current;;
                        i++;
                    }
                } else if (c == 'X') {
                    if (s.charAt(i) == 'L') {
                        current = map.get('L') - current;
                        i++;
                    } else if (s.charAt(i) == 'C') {
                        current = map.get('C') - current;
                        i++;
                    }
                } else if (c == 'C') {
                    if (s.charAt(i) == 'D') {
                        current = map.get('D') - current;
                        i++;
                    } else if (s.charAt(i) == 'M') {
                        current = map.get('M') - current;;
                        i++;
                    }
                }
            }
            ret +=current;
        }

        return ret;
    }
}
