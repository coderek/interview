package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class ParenChecker {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        for (char c: s.toCharArray()) {
            if ("({[".contains(c+"")) {
                stack.addLast(c);
            } else if (")}]".contains(c+"")) {
                Character lc = stack.peekLast();
                if (lc ==null) return false;
                if (lc=='(' && c==')' || lc=='[' && c==']' || lc=='{' && c=='}') {
                    stack.pollLast();
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
