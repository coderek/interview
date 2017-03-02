package me.derekzeng.code.good;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class MaxRectangle {
    int solve(char[][] matrix) {
        int ret=0;
        int l = matrix.length;
        if (l==0) return ret;
        int ll = matrix[0].length;
        if (l==0) return ret;

        int[] left = new int[ll];
        int[] right = new int[ll];
        int[] height = new int[ll];

        Arrays.fill(right, ll);

        for (int i=0;i<l;i++) {
            int curLeft=0, curRight=ll;
            for (int j=ll-1;j>=0;j--) {
                if (matrix[i][j]=='1') {
                    right[j] = min(right[j], curRight);
                } else {
                    right[j] = ll;
                    curRight=j;
                }
            }
            for (int j=0;j<ll;j++) {
                if (matrix[i][j]=='1') {
                    left[j] = max(left[j], curLeft);
                    height[j]++;
                } else {
                    left[j] = 0;
                    curLeft=j+1;
                    height[j]=0;
                }
                ret = Math.max(ret, (right[j]-left[j])*height[j]);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        MaxRectangle mr = new MaxRectangle();

        char[][] input = new char[6][7];
        int i=0;
        for (String s: new String[]{
                "0100010",
                "1100010",
                "1100010",
                "1100010",
                "1100111",
                "1101111"}) {
            input[i++]= s.toCharArray();
        }
        System.out.println(mr.solve(input));
    }
}
