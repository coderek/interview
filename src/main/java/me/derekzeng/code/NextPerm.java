package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class NextPerm {
    public void nextPermutation(int[] nums) {
        int anchor=-1;
        int l=nums.length;
        for (int i = l-1; i >= 0; i--) {
            if (i>0 && nums[i]>nums[i-1]) {
                anchor=i-1;
                break;
            }
        }
        int s=anchor+1;
        for (int i=s;i<(l+s)/2;i++) {
            int tmp=nums[i];
            nums[i] = nums[l+s-i-1];
            nums[l+s-i-1]=tmp;
        }
        if (anchor==-1) return;
        for (int i = anchor+1; i < l; i++) {
            if (nums[i]>nums[anchor]) {
                int tmp=nums[i];
                nums[i] = nums[anchor];
                nums[anchor]=tmp;
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
