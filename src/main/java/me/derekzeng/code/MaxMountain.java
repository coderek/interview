package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class MaxMountain {
    /*
       // Tests
        res = mm.solve(new int[]{2,4,5,3,6,4,7,3});
        assertTrue(res[0] == 3);
        assertTrue(res[1] == 7);
        res = mm.solve(new int[]{2,3,4,5});
        assertTrue(res[0] == 0);
        assertTrue(res[1] == 1);
        res = mm.solve(new int[]{4,3,2,3,5,6,8,4});
        assertTrue(res[0] == 3);
        assertTrue(res[1] == 7);
        res = mm.solve(new int[]{4,3,0,4,4,3,1});
        assertTrue(res[0] == 2);
        assertTrue(res[1] == 6);
    */

    public int[] solve(int[] terrain) {
        int l = terrain.length;
        int[] interval = new int[2];
        int i=0;
        int start=0;

        while (i<terrain.length) {
            int h = terrain[i];
            if (h <= terrain[start]) {
//                 System.out.println("valley: "+i+" " +start+" "+interval[0]+" "+interval[1]);
                if (i-start > interval[1] - interval[0]) {
                    interval[0] = start;
                    interval[1] = i;
                }
                start = i;
            } else if (i==terrain.length-1) {
                int end=l-1;
                int min=-1;
                while (end > start) {
                    if (min == -1 || terrain[min] >= terrain[end]) {
                        min = end;
                    }
                    end--;
                }
                end=start;
//                 System.out.println("start:"+start+ " min: "+min);
                while (end<=min) {
                    if (terrain[end] >= terrain[min]) {
                        break;
                    }
                    end++;
                }

                if (end==start+1) {
                    end=min;
                } else {
                    start = end-1;
                }

//                 System.out.println("end: "+end);
                if (end-start > interval[1] - interval[0]) {
                    interval[0] = start;
                    interval[1] = end;
                }
                start = min;
                i = min;
            }
            i=i+1;
        }
//         System.out.println(interval[0] + " " + interval[1]);
        return interval;
    }
}
