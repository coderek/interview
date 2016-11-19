package me.derekzeng.code;

import java.io.*;
import java.util.*;

public class Capacitor {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Set<Double> set = new HashSet<>();
        
        int n = s.nextInt();
        LinkedList<Double> row = new LinkedList<>();
        row.add(60.0);
        set.add(60.0);
        
        for (int i = 1; i< n; i ++) {
            int size = row.size();
            for (int j = 0; j < size; j ++) {
                Double num = row.removeFirst();
                Double added = num + 60;
                Double divided = num * 60 / (num + 60);
                set.add(added);
                set.add(divided);
                row.addLast(added);
                row.addLast(divided);
            }
        }

        System.out.println(set.size());
    }
}
