package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class AllOOne {
    class Node {
        String key;
        int count=1;
        Node prev=null;
        Node next=null;
        Node(String key) {
            this.key = key;
        }
    }

    Map<String, Node> map = new HashMap<>();
    Node head=null;
    Node tail=null;

    /** Initialize your data structure here. */
    public AllOOne() {
        head = new Node("");
        head.count = Integer.MAX_VALUE;
        tail = new Node("");
        tail.count = Integer.MIN_VALUE;
        head.next = tail;
        tail.prev = head;
    }


    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (map.containsKey(key)) {
            Node k = map.get(key);
            k.count++;

            while (k.count > k.prev.count) {
                Node prev = k.prev;

                prev.prev.next = k;
                k.prev = prev.prev;

                prev.next = k.next;
                k.next.prev = prev;

                k.next = prev;
                prev.prev = k;
            }
        } else {
            Node node = new Node(key);
            map.put(key, node);

            tail.prev.next = node;
            node.prev = tail.prev;
            tail.prev = node;
            node.next = tail;
        }
    }


    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
            Node k = map.get(key);
            if (k.count == 1) {
                map.remove(key);
                k.prev.next = k.next;
                k.next.prev = k.prev;

                k.next = null;
                k.prev = null;
            } else {
                k.count--;
                while (k.next.count > k.count) {
                    Node next = k.next;
                    k.prev.next = next;
                    next.prev = k.prev;

                    k.next = next.next;
                    next.next.prev = k;

                    k.prev = next;
                    next.next = k;
                }
            }
        }
    }


    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        print();
        if (head.next == null) return "";
        return head.next.key;
    }
    void print() {
        Node n = head;
        while (n!=null) {
            System.out.print(n.key + " ");
            n = n.next;
        }
        System.out.println();
    }


    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        print();
        if (tail.prev == null) return "";
        return tail.prev.key;
    }
}


/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
// }
