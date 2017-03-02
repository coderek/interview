package me.derekzeng.code;
import java.util.*;
import java.io.*;



public class WordDictionary {
    class Node {
        char ch;
        boolean isRoot;
        Map<Character, Node> children = new HashMap<>();
        String word = null;

        Node(char _ch) {
            ch = _ch;
        }

        Node(boolean _isRoot) {
            isRoot = _isRoot;
        }

//         public int hashCode() {return 1;}
//         public String toString() {
//             return new String(ch);
//         }
    }



    /** Initialize your data structure here. */

    Node root = new Node(true);
    public WordDictionary() {

    }



    /** Adds a word into the data structure. */

    public void addWord(String word) {
        Node n = root;
        for (Character ch: word.toCharArray()) {
            if (n.children.containsKey(ch)) {
                n = n.children.get(ch);
            } else {
                Node chNode = new Node(ch);
                n.children.put(ch, chNode);
                n = chNode;
            }
        }
        n.word = word;
    }



    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */

    public boolean search(String word) {
        return _search(word, root.children);
    }

    boolean _search(String partial, Map<Character, Node> children) {
        for (int i=0;i<partial.length();i++) {
            Character ch = partial.charAt(i);

            if (ch == '.') {
                for (Node child: children.values()) {
                    if (i == partial.length() -1 && child.word != null) return true;
                    if (_search(partial.substring(i+1), child.children)) return true;
                }
                return false;
            } else if (children.containsKey(ch)) {
                Node n = children.get(ch);
                if (i == partial.length() -1 && n.word != null) {
                    return true;
                }
                children = n.children;
            } else {
                return false;
            }
        }
        return false;
    }

}
