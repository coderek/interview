package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class SmallerNumbers {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ret = new ArrayList<>(nums.length);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(Integer.MIN_VALUE,0);
        for (int i=0;i<nums.length;i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> index = new ArrayList<>(map.entrySet());
        Collections.sort(index, (a,b)->a.getKey()<b.getKey()?-1:1);

        for (int i=0;i<nums.length;i++) {
            int n = nums[i];
            int idx = search(index, n);

            int freq = idx>1?find(index, idx-1):0;
            ret.add(freq);
            System.out.println(index);
            System.out.println(idx+" " +n);
            update(index, idx, 1);
        }

        return ret;
    }

    int search(List<Map.Entry<Integer, Integer>> index, int key) {
        int l=0, r=index.size();
        while (l<r) {
            int m = l+(r-l)/2;
            if (index.get(m).getKey()==key) return m;
            if (index.get(m).getKey()>key) {
                r=m;
            } else {
                l=m+1;
            }
        }
        return l;
    }

    int find(List<Map.Entry<Integer, Integer>> index, int idx) {
        int sum=0;
        while (idx>0) {
            sum+=index.get(idx).getValue();
            idx-=(idx&-idx);
        }
        return sum;
    }

    void update(List<Map.Entry<Integer, Integer>> index, int idx, int diff) {
            System.out.println(idx);
        while (idx<index.size()) {
            index.get(idx).setValue(index.get(idx).getValue()+diff);
            idx+=(idx&-idx);
        }
    }

    public static void main(String[] args) {
        SmallerNumbers sn = new SmallerNumbers();
        sn.countSmaller(new int[]{5,2,6,1});
    }
}
