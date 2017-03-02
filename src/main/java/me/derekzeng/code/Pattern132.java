package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class Pattern132 {
    class Pair {
        int min;
        int max;
        Pair(int _min, int _max) {
            min = _min;
            max = _max;
        }
    }


    public boolean find132pattern(int[] nums) {
        LinkedList<Pair> stack = new LinkedList<>();

        for (int n: nums) {
            if (stack.isEmpty()) {
                stack.push(new Pair(n, n));
            } else if (n >= stack.peek().max) {
                Pair last = stack.pop();
                last.max = n;
                while (!stack.isEmpty() && stack.peek().max <= last.max) {stack.pop();}
                if (!stack.isEmpty() && stack.peek().min < last.max) {return true;}
                stack.push(last);
            } else if (n > stack.peek().min) {
                return true;
            } else {
                stack.push(new Pair(n, n));
            }
        }
        return false;
    }
}
