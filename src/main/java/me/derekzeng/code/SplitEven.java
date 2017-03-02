package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class SplitEven {
    List<List<String>> solve(String s) {
        List<List<String>> ret = new ArrayList<>();
        int Z =0, O=0;
        for (int i=0;i<s.length();i++) {
            if (s.charAt(i) == '0') {
                Z+=1;
            } else {
                O+=1;
            }
        }
        int w = s.length()/2;
        int z=0,o=0;
        int i=0, j=1;

        for (;j<=s.length();j++) {
            if (j<=w) {
                if (s.charAt(j-1)=='0') {
                    z+=1;
                } else {
                    o+=1;
                }
            } else {
                if (s.charAt(j-1) == '0') {
                    z+=1;
                } else {
                    o+=1;
                }
                if (s.charAt(i) == '0') {
                    z-=1;
                } else {
                    o-=1;
                }
                i++;
                if (z==Z/2 && o==O/2) {
                    List<String> split = new ArrayList<>();
                    if (i>0) {
                        split.add(s.substring(0,i));
                    }
                    split.add(s.substring(i,j));
                    if (j<s.length()) {
                        split.add(s.substring(j));
                    }
                    ret.add(split);
                }
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        SplitEven lp = new SplitEven();
        System.out.println(lp.solve("1111000000"));
        System.out.println(lp.solve("1101100000"));
        System.out.println(lp.solve("1101010011"));
        System.out.println(lp.solve("0101001010"));
    }
}
