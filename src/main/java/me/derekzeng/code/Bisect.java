package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class Bisect {
    public static int bisectLeft(int[] arr, int x, int lo, int hi) {
        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            if (arr[mid] == x) {
                if (mid == 0 || arr[mid-1] < x) {
                    return mid;
                } else {
                    hi = mid;
                }
            } else if (arr[mid] > x) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        return hi;
    }

    public static int bisectRight(int[] arr, int x, int lo, int hi) {
        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            if (arr[mid] == x) {
                if (mid == arr.length-1 || arr[mid+1] > x) {
                    return mid+1;
                } else {
                    lo = mid+1;
                }
            } else if (arr[mid] > x) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        return hi;
    }
}
