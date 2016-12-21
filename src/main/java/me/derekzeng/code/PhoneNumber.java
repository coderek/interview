package me.derekzeng.code;

import java.util.*;
import java.io.*;


public class PhoneNumber {
    String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        recurse(sb, digits.toCharArray(), 0, res);
        return res;
    }

    private void recurse(StringBuilder sb, char[] digits, int cur, List<String> res) {
        if (cur == digits.length) {
            if (sb.length() > 0) res.add(sb.toString());
            return;
        }
        for (char c: mapping[digits[cur] - '0'].toCharArray()) {
            sb.append(c);
            recurse(sb, digits, cur + 1, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        String abc = "23";
        PhoneNumber pn = new PhoneNumber();
        pn.letterCombinations(abc);
    }
}
