package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class WordLadder2 {
    List<List<String>> result;
    Map<String, Set<String>> prevs;
    Map<String, Integer> dist;

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        result = new ArrayList<>();
        if (wordList.size() == 0) {
            return result;
        }
        wordList.add(endWord);

        LinkedList<String> queue = new LinkedList<>();
        prevs = new HashMap<>();
        dist = new HashMap<>();

        // initialize
        for (String w: wordList) {
            dist.put(w, Integer.MAX_VALUE);
            prevs.put(w, new HashSet<>());
        }


        dist.put(beginWord, 0);

        queue.add(beginWord);
        while (!queue.isEmpty()) {
            String w = queue.poll();

            // suppose we are on the shortest path
            int nextDist = dist.get(w) + 1;

            StringBuilder sb;
            for (int i=0;i<w.length();i++) {
                sb = new StringBuilder(w);

                for (char j='a';j<='z';j++) {
                    sb.setCharAt(i, j);
                    String newWord = sb.toString();

                    if (wordList.contains(newWord)) {
                        if (dist.get(newWord) >= nextDist) {
                            if (dist.get(newWord) != nextDist) {
                                queue.add(newWord);
                            }

                            // we update the distance
                            dist.put(newWord, nextDist);
                            prevs.get(newWord).add(w);
                        }
                    }
                }
            }
        }

        System.out.println(dist);
        System.out.println(prevs);

        List<String> path = new ArrayList<String>();
        path.add(endWord);
        backTrack(path, beginWord);

        return result;
    }

    void backTrack(List<String> path, String beginWord) {
        String last = path.get(0);
        if (last.equals(beginWord)) {
            result.add(new ArrayList(path));
            return;
        }
        for (String s: prevs.get(last)) {
            path.add(0, s);
            backTrack(path, beginWord);
            path.remove(0);
        }
    }
}
