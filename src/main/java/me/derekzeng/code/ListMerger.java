package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class ListMerger {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l=l1;
        ListNode r=l2;
        ListNode s=null;
        ListNode e=null;
        while (l!=null && r!=null) {
            ListNode n=null;
            if (l.val > r.val) {
                n = r;
                r=r.next;
            } else {
                n = l;
                l=l.next;
            }

            if (s == null) {
                s=n;
                e=s;
            } else {
                e.next = n;
                e = n;
            }
        }

        if (l!=null) {
            if (e!=null) {
                e.next = l;
            } else {
                return l;
            }
        }
        if (r!=null) {
            if (e!=null) {
                e.next = r;
            } else {
                return r;
            }
        }
        return s;
    }
}
