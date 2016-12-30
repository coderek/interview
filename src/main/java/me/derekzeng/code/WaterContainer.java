package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class WaterContainer {

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;

        while (l < r) {
            max = Math.max(Math.min(height[l],height[r]) * Math.abs(l-r), max);

            if (height[l] < height[r]) {
                l ++;
            } else {
                r --;
            }
        }
        return max;
    }
}
