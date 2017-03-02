package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class ParenGenerator {
    List<String> strs = new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder();
        generate(n, n, sb);
        return strs;
    }

    void generate(int o, int c, StringBuilder sb) {
        if (o == 0 && c == 0) {
            strs.add(sb.toString());
        } else {
            // must ensure o<=c
            if (o==c) {
                sb.append('(');
                generate(o-1, c, sb);
                sb.deleteCharAt(sb.length()-1);
            } else if (o < c) {

                if (o>0) {
                    sb.append('(');
                    generate(o-1, c, sb);
                    sb.deleteCharAt(sb.length()-1);
                }

                if (c>0) {
                    sb.append(')');
                    generate(o, c-1, sb);
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
    }
}
