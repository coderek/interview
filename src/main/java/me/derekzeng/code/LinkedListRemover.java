package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class LinkedListRemover {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int l = 0;
        ListNode p = head;
        while (p!=null) {l++;p=p.next;}

        n = l - n;
        ListNode prev = null;
        p = head;
        while (n>0) {
            prev = p;
            p=p.next;n--;
        }
        if (prev!=null) {
            prev.next = p.next;
        } else {
            p = head;
            head = p.next;
        }
        p.next = null;

        return head;
    }
}
