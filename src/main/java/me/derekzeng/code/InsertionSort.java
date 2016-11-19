package me.derekzeng.code;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class InsertionSort {
    public static void insertionSortPart2(int[] ar) {
        for (int j = 0; j < ar.length; j++) {
            int toInsert = ar[j];
            for (int i = j - 1; i >= -1; i --) {
                if (i == -1) {
                    ar[0] = toInsert;
                    break;
                } else if (ar[i] > toInsert) {
                    ar[i + 1] = ar[i];
                } else {
                    ar[i + 1] = toInsert;
                    break;
                }
            }
            printArray(ar);
        }
    }
    
      public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
       int s = in.nextInt();
       int[] ar = new int[s];
       for(int i=0;i<s;i++){
            ar[i]=in.nextInt(); 
       }
       insertionSortPart2(ar);    
                    
    }    
    private static void printArray(int[] ar) {
      for(int n: ar){
         System.out.print(n+" ");
      }
        System.out.println("");
   } 
}

