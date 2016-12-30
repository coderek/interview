package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class SinglyLinkedPalindrome {

    public boolean isPalindrome(ListNode head) {
        int len = 0;
        ListNode h = head;
        while (h != null) {
            len++;
            h = h.next;
        }

        int half = (len + 1) /2;
        ListNode start = head;

        while (half != 0) {
            start = start.next;
            half--;
        }

        ListNode r = reverse(start);
        ListNode rev = r;
        h = head;
        while (rev != null) {
            System.out.println(rev.val + " " + h.val);
            if (rev.val == h.val) {
                rev = rev.next;
                h = h.next;
            } else {
                return false;
            }
        }
        reverse(r);
        return true;
    }

    public ListNode reverse(ListNode s) {
        ListNode prev = null;
        while (s != null) {
            ListNode n = s.next;
            s.next = prev;
            prev = s;
            s = n;
        }
        return prev;
    }
}
