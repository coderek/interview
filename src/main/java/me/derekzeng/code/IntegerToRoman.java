package me.derekzeng.code;

import java.util.*;
import java.io.*;


class IntegerToRoman {
    public String intToRoman(int num) {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        StringBuilder res = new StringBuilder();
        for (Integer i: map.keySet()) {
            while (num >= i) {
                res.append(map.get(i));
                num -= i;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman itr = new IntegerToRoman();
        System.out.println(itr.intToRoman(1066));
    }
}
