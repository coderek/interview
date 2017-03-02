package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        int missing = t.length();
        int start=0;
        int minStart=-1, minEnd=-1;

        Map<Character, Integer> need = new HashMap<>();
        for (char ch: t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0)+1);
        }

        // initialize start
        while (start<s.length() && !need.containsKey(s.charAt(start))) {start++;}

        for (int i=start;i<s.length();i++) {
            char ch = s.charAt(i);
            if (need.containsKey(ch)) {
                need.put(ch, need.get(ch)-1);
                // extra won't affect missing count
                missing-=need.get(ch)>=0?1:0;
                while (start < i) {
                    char c = s.charAt(start);
                    if (need.getOrDefault(c, -1) < 0) {
                        start++;
                        if (need.containsKey(c)) {need.put(c, need.get(c)+1);}
                    } else {
                        break;
                    }
                }
            }

            if (missing==0) {
                if (minStart==-1 || minEnd-minStart>i-start) {
                    minEnd=i;
                    minStart=start;
                }
            }
            // invariant
            // the leading character do not have extra
        }

        if (minStart==-1) return "";
        return s.substring(minStart, minEnd+1);
    }

    public static void main(String[] args) {
        MinWindowSubstring mws = new MinWindowSubstring();
        System.out.println(mws.minWindow("cabefgecdaecf", "cae"));
    }
}
