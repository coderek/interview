package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class MissingPositiveInteger {
    int solve(int[] nums) {
        for (int i=0;i<nums.length;i++) {
            if (nums[i] != i+1) {
                int j = nums[i];
                nums[i] = -1;
                while (j>0 && j<=nums.length && nums[j-1] != j) {
                    int tmp = nums[j-1];
                    nums[j-1] = j;
                    j = tmp;
                }
            }
        }
        for (int i=0;i<nums.length;i++) {
            if (nums[i]!=i+1) {
                return i+1;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        MissingPositiveInteger mp = new MissingPositiveInteger();
        System.out.println(mp.solve(new int[]{4,3,2,5,6,7,1}));
    }
}
