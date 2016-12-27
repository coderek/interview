package me.derekzeng.code;
import java.util.*;
import java.io.*;

class WordBreak2 {
    public List<String> wordBreak(String str, Set<String> wordDict) {
        int len = str.length();
        boolean[][] table = new boolean[len+1][len+1];
        for (int e = 1; e < table.length; e++) {
            for (int i=0;i<e;i++) {
                if (!table[0][i]) continue;
                String word = str.substring(i, e);
                if (wordDict.contains(word)) {
                    table[0][e] = true;
                }
            }

            for (int s = 1; s < table.length-1; s++) {
                if (s>=e) continue;
                String word = str.substring(s, e);
                if (table[0][s] && wordDict.contians(word)) {
                    table[s][e] = true;
                }
            }
            for (int n = 0; n < table.length; n++) {
                System.out.print(table[n][e] + "(" + n + "," + e +  ") ");
            }
            System.out.println();
        }
        // empty
        if (!table[0][len]) return new LinkedList<>();

        List<String> holder = new LinkedList<>();
        getRes(str, wordDict, table, holder, len, new LinkedList<String>());

        return holder;
    }

    private void getRes(String str, Set<String> wordDict, boolean[][] table, List<String> holder, int e, List<String> list) {
        for (int s = 0; s < table.length - 1; s++) {
            if (table[s][e]) {
                String word = str.substring(s, e);
                if (wordDict.contains(word)) {
                    list.add(word);
                    if (s == 0) {
                        holder.add(String.join(" " , list));
                    } else {
                        getRes(str, wordDict, table, holder, s, list);
                    }
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        WordBreak2 wb = new WordBreak2();
        Set<String> s = new HashSet<>();
        s.add("cat");
        s.add("cats");
        s.add("dog");
        s.add("and");
        s.add("sand");
        List<String> res = wb.wordBreak("catsanddog", s);
        for (String str: res) {
            System.out.println(str);
        }
    }
}
