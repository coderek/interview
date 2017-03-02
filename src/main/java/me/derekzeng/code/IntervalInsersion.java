package me.derekzeng.code;
import java.util.*;
import java.io.*;



public class IntervalInsersion {
//     List<Interval> intervals;

//     public List<Interval> insert(List<Interval> intervals_, Interval newInterval) {
//         intervals = intervals_;
// 
//         if (intervals.size()==0) {
//             intervals.add(newInterval);
//             return intervals;
//         }
// 
//         int start = newInterval.start;
//         int end = newInterval.end;
// 
//         // add padding to eliminate edge cases
//         intervals.add(0, new Interval(Integer.MIN_VALUE, Integer.MIN_VALUE));
//         intervals.add(intervals.size(), new Interval(Integer.MAX_VALUE, Integer.MAX_VALUE));
// 
//         int l = 0;
//         int r = intervals.size()-1;
// 
//         int starti = bsearch(start, true);
//         int endi= bsearch(end, false);
// 
//         int sliceStart, sliceEnd;
//         // we have four cases here
//         if (intervals.get(starti).end < start && intervals.get(endi).start > end) {
//             // neither start nor end is in an interval
//             sliceStart = starti+1;
//             sliceEnd = endi;
//         } else if (intervals.get(starti).end < start) {
//             // only end is in a interval
//             end = intervals.get(endi).end;
//             sliceStart = starti+1;
//             sliceEnd = endi+1;
//         } else if (intervals.get(endi).start > end) {
//             // only start is in a interval
//             start = intervals.get(starti).start;
//             sliceStart = starti;
//             sliceEnd = endi;
//         } else {
//             // both start and end are in an interval
//             start = intervals.get(starti).start;
//             end = intervals.get(endi).end;
//             sliceStart = starti;
//             sliceEnd = endi+1;
//         }
// 
//         intervals.subList(sliceStart, sliceEnd).clear();
//         intervals.add(sliceStart, new Interval(start, end));
// 
//         // remove the padding
//         intervals.remove(0);
//         intervals.remove(intervals.size()-1);
// 
//         return intervals;
//     }
// 
//     int bsearch(int target, boolean left) {
//         int l = 0;
//         int r = intervals.size()-1;
// 
//         int pos = -1;
//         while (l<r-1) {
//             int m = (l+r)/2;
//             Interval iter = intervals.get(m);
//             if (iter.start <= target && iter.end >= target) {
//                 pos= m;
//                 break;
//             } else if (iter.start > target) {
//                 r = m;
//             } else {
//                 l = m;
//             }
//         }
//         if (pos== -1) {
//             pos= left? l: r;
//         }
//         return pos;
//     }
}
