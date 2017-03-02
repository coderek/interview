package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class SwapPairs {

    public ListNode swapPairs(ListNode node) {
        if (node == null) {
            return null;
        }

        ListNode head = node;
        ListNode a = null;
        ListNode b = node;
        ListNode c = node.next;
        int move = 0;

        while (c!=null) {
            if (move % 2 == 0) {
                b.next = c.next;
                c.next = b;
                if (a != null) {
                    a.next = c;
                } else {
                    head = c;
                }
                a = c;
                c = b.next;
            } else {
                a = b;
                b = c;
                c = c.next;
            }
            move++;
        };

        return head;
    }
}
