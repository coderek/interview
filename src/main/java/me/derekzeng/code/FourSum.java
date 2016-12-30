package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        int l = nums.length;
        if (l < 4) return ret;

        Arrays.sort(nums);
        for (int i: nums) {
//             System.out.print(i + " ");
        }
//         System.out.println();

        for (int i = 0; i < l; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
//             System.out.println("forsum start: "+ nums[i]);
            List<List<Integer>> threeSums = threeSum(nums, i+1, target - nums[i]);
            for (List<Integer> ts: threeSums) {
                ts.add(0, nums[i]);
                ret.add(ts);
            }
        }

        for (List<Integer> r:ret) {
//             System.out.println(r);
        }
        return ret;
    }

    List<List<Integer>> threeSum(int[] nums, int start, int target) {
        int l = nums.length;
        List<List<Integer>> ret = new LinkedList<>();

        for (int i = start; i < l; i++) {
            if (i > start && nums[i] == nums[i-1]) continue;
//             System.out.println("threesum start: "+ nums[i]);
            List<List<Integer>> twoSums = twoSum(nums, i+1, target - nums[i]);
            for (List<Integer> ts: twoSums) {
                ts.add(0, nums[i]);
                ret.add(ts);
            }
        }
        return ret;
    }
    List<List<Integer>> twoSum(int[] nums, int start, int target) {
//         System.out.println("two sum target: "+target);
        int l = start;
        int r = nums.length - 1;
//         System.out.println(l + " " + r);
        List<List<Integer>> ret = new LinkedList<>();

        while (l < r) {
            int s = nums[l] + nums[r];
            if (s == target) {
                List<Integer> ts = new LinkedList<>();
                ts.add(nums[l]);
                ts.add(nums[r]);
                ret.add(ts);
            }
            if (s <= target) {
                do {l++;} while (l < r && nums[l] == nums[l-1]);
            } else {
                do {r--;} while (l < r && nums[r] == nums[r+1]);
            }
        }
        return ret;
    }
}
