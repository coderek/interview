package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class LongestPalindrome {
    public int longestPalindromeSubseq(String s) {
        int l = s.length();
        int[][] memo = new int[l][l];
        int ret = sub(s.toCharArray(), 0, s.length()-1, memo);
        return ret;
    }

    int sub(char[] letters, int start, int end, int[][] memo) {
        if (start == end) return 1;
        if (start > end) return 0;

        if (memo[start][end]!=0) return memo[start][end]-1;

        int ret=1;
        for (int j=start;j<end;j++) {
            char letter = letters[j];

            for (int i=end;i>j;i--) {
                if (letter == letters[i]) {
                    ret = Math.max(ret, 2 + sub(letters, j+1, i-1, memo));
                    break;
                }
            }
        }
        memo[start][end] = ret+1;
        return ret;
    }
}
