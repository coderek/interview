package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class Jump {

    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[nums.length-1] = 0;

        for (int i=nums.length-2;i>=0;i--) {
            int dis = nums[i];
            int min = Integer.MAX_VALUE;
            for (int j=i;j<nums.length && j<i+dis+1;j++) {
                min = Math.min(min, dp[j] + 1);
            }
        }
        return dp[0];
    }
}
