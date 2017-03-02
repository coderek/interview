package me.derekzeng.code;
import java.util.*;
import java.io.*;

public class KListMerger {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0) return null;
        if (lists.length==1) return lists[0];

        ListNode merged=lists[0];

        for (int i=1;i<lists.length;i++) {
            merged = mergeTwoLists(lists[i], merged);
        }
        return merged;
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a==null) return b;
        if (b==null) return a;

        ListNode start=null;
        ListNode end=null;

        while (a!=null & b!=null) {
            if (a.val > b.val) {
                if (start==null) {
                    start=b;
                    end=b;
                } else {
                    end.next=b;
                    end=b;
                }
                b=b.next;
            } else {
                if (start==null) {
                    start=a;
                    end=a;
                } else {
                    end.next=a;
                    end=a;
                }
                a=a.next;
            }
        }

        if (a!=null) {
            if (end!=null) {
                end.next = a;
            } else {
                return a;
            }
        }
        if (b!=null) {
            if (end!=null) {
                end.next = b;
            } else {
                return b;
            }
        }
        return start;
    }
}
