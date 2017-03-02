package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> ls = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (n % 3 ==0 && n % 5 == 0) {
                ls.add("FizzBuzz");
            } else if (n % 3 == 0) {
                ls.add("Fizz");
            } else if (n % 5 == 0) {
                ls.add("Buzz");
            } else {
                ls.add("" + i);
            }
        }
        return ls;
    }
}
