package me.derekzeng.code;

import java.util.*;


class SubstringAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new LinkedList<>();
        if (words.length ==0) return ret;

        int wl = words[0].length();
        int l = words.length;
        int sl = s.length();

        Map<String, Integer> counter = new HashMap<>();
        for (String w:words) {
            counter.put(w, counter.getOrDefault(w, 0) + 1);
        }
        Map<String, Integer> collector = new HashMap<>();
        int collectedSoFar;
        for (int i=0;i<wl;i++) {
            collector.clear();
            collectedSoFar = 0;

            for (int j=i, r=i;r+wl<=sl;r+=wl) {
                String word = s.substring(r, r+wl);
                if (!counter.containsKey(word)) {
                    collectedSoFar = 0;
                    collector.clear();
                    j=r+wl;
                    continue;
                }
                collectedSoFar++;
                collector.put(word, collector.getOrDefault(word, 0) +1);

                while (collector.get(word) > counter.get(word)) {
                    String tmp = s.substring(j, j+wl);
                    collector.put(tmp, collector.get(tmp)-1);
                    collectedSoFar--;
                    j+=wl;
                    System.out.println("drop word: " + tmp);
                }
                System.out.print("collectedSoFar: " + collectedSoFar);
                System.out.println(collector);

                if (collectedSoFar == l) {
                    ret.add(j);
                    String leader = s.substring(j, j+wl);
                    System.out.println(leader);
                    collector.put(leader, collector.get(leader)-1);
                    collectedSoFar--;
                    j+=wl;
                }
            }
        }
        return ret;
    }
}
