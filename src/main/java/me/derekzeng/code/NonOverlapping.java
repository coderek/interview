package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class NonOverlapping {
    public static class Interval {
        int start=0;
        int end=0;
        Interval(int s, int e) { start = s; end = e; }
    }

    static class Segment {
        int start=0;
        int end=0;
        Segment(int s, int e) { start = s; end = e; }
        public int hashCode() {return 1;}
        public boolean equals(Object _o) {
            Segment o = (Segment) _o;
            return o.start == start && o.end == end;
        }
        public String toString() {
            return "(" + start + "," + end + ")";
        }
    }

    public int eraseOverlapIntervals(Interval[] intervals) {
        Map<Segment, Set<Integer>> map = new HashMap<>();
        Arrays.sort(intervals, (a,b)->{
            return a.start != b.start? a.start - b.start:a.end - b.end;
        });

        int n = intervals.length;
        for (int i=0;i<n;i++) {
            Interval inter = intervals[i];
            for (int j=i+1;j<n;j++) {
                Interval inter2 = intervals[j];
                if (inter.end <= inter2.start) {
                    continue;
                }
                Segment s=null;

                if (inter.end >= inter2.end) {
                    s = new Segment(inter2.start, inter2.end);
                } else {
                    s = new Segment(inter2.start, inter.end);
                }

                if (!map.containsKey(s)) {
                    map.put(s,new HashSet<>()); }
                map.get(s).add(j);
                map.get(s).add(i);
            }
        }
        System.out.println(map);
        return map.values().stream().mapToInt(a->a.size()).max().orElse(0) - 1;
    }
}
