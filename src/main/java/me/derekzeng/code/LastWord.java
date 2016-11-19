package me.derekzeng.code;

import java.io.*;
import java.util.*;


class LastWord {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        for (int i=0;i<n;i++) {
            String res = solve(s.next());
            System.out.printf("Case #%s: %s\n", i+1, res);
        }
    }

    static class Letter {
        Letter prev;
        Letter next;
        char ch;

        Letter(char ch) {this.ch = ch;}
    }

    private static String solve(String word) {
        Letter head = new Letter(word.charAt(0));
        Letter tail = head;

        for (int i=1;i<word.length();i++) {
            Letter l = new Letter(word.charAt(i));
            if (l.ch >= head.ch) {
                head.prev = l;
                l.next = head;
                head = l;
            } else {
                tail.next = l;
                tail = l;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.ch);
            head = head.next;
        }
        return sb.toString();
    }
}
