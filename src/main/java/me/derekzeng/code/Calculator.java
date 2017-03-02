package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class Calculator {
    class Token {
        /**
         * 0: number
         * 1: plus
         * 2: minus
         * 3: open bracket
         * 4: close bracket
         */
        int type;
        String value;
        Token(int _type, String _value) {
            type = _type;
            value = _value;
        }

        public String toString() {
            return value;
        }

        int getIntValue() {
            StringBuilder sb = new StringBuilder(value).reverse();
            int val=0;
            boolean negative = false;
            for (int i=0;i<sb.length();i++) {
                char ch = sb.charAt(i);
                if (ch == '-') {
                    negative = true;
                } else {
                    val += Math.pow(10.0, (double) i) * (ch-'0');
                }
            }
            if (negative) return -val;
            else return val;
        }
    }
    public int calculate(String s) {
        s = s.replace(" ", "");
        List<Token> tokens = new ArrayList<>();

        int i=0;
        while (i<s.length()) {
            switch (s.charAt(i)) {
                case '(':
                    tokens.add(new Token(3, "("));
                    break;
                case ')':
                    tokens.add(new Token(4, ")"));
                    break;
                case '+':
                    tokens.add(new Token(1, "+"));
                    break;
                case '-':
                    tokens.add(new Token(2, "-"));
                    break;
                default:
                    // number
                    String n = s.charAt(i) + "";
                    int j=i+1;
                    while (j<s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                        n = n + s.charAt(j++);
                    }
                    tokens.add(new Token(0, n));
                    i=j;
                    continue;
            }
            i++;
        }
        System.out.println(tokens);

        // parse
        LinkedList<List<Token>> stack = new LinkedList<>();
        stack.push(new ArrayList<>());

        for (Token token: tokens) {
            if (token.type == 3) {
                stack.push(new ArrayList<>());
            } else if (token.type == 4) {
                List<Token> expr = stack.pop();
                int n = evaluate(expr);
                stack.peek().add(new Token(0, "" + n));
            } else {
                stack.peek().add(token);
            }
        }
        int res = evaluate(stack.pop());
        System.out.println(res);
        return res;
    }

    int evaluate(List<Token> tokens) {
        int res = 0;
        int op = 1;

        for (Token t: tokens) {
            if (t.type == 0) {
                res += op * t.getIntValue();
            } else if (t.type == 1) {
                op = 1;
            } else {
                op = -1;
            }
        }
        System.out.println(tokens + " = " + res);
        return res;
    }
}
