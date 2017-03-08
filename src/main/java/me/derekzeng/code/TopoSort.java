package me.derekzeng.code;
import java.util.*;
import java.util.stream.*;
import java.io.*;


public class TopoSort {
    public static void main(String[] args) {
        TopoSort tsort = new TopoSort();
        int[] org = {1,2,3};
        List<List<Integer>> seqs = new ArrayList<>();
        seqs.add(Arrays.asList(1,2));
        seqs.add(Arrays.asList(1,3));
        seqs.add(Arrays.asList(2,3));
        System.out.println(tsort.sequenceReconstruction(org, seqs));
    }
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> adjs = new HashMap<>();
        Set<Integer> available = seqs.stream().flatMap(a->a.stream()).collect(Collectors.toSet());
        if (available.size()!=org.length) return false;

        for (List<Integer> seq: seqs) {
            Set<Integer> set = new HashSet<>(seq);
            for (int i: org) set.remove(new Integer(i));
            if (!set.isEmpty()) return false;

            Integer[] nums = seq.toArray(new Integer[0]);
            for (int i=0;i<nums.length-1;i++) {
                Set<Integer> neighbors = adjs.getOrDefault(nums[i], new HashSet<>());
                neighbors.add(nums[i+1]);
                adjs.put(nums[i], neighbors);
            }
        }

        for (int i=0;i<org.length-1;i++) {
            if (!adjs.containsKey(org[i]) || !adjs.get(org[i]).contains(org[i+1])) return false;
        }
        return true;
    }
}
