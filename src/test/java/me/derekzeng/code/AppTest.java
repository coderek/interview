package me.derekzeng.code;

import static me.derekzeng.code.NonOverlapping.Interval;
import java.util.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.lang.InterruptedException;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

//     @Test
    public void easy() {
        assertTrue(Bisect.bisectLeft(new int[]{1,3,3,5,6,7,7,7,7,8}, 2, 0, 9) == 1);
        assertTrue(Bisect.bisectLeft(new int[]{1,3,3,5,6,7,7,7,7,8}, 2, 4, 9) == 4);
        assertTrue(Bisect.bisectLeft(new int[]{1,3,3,5,6,7,7,7,7,8}, 2, 0, 0) == 0);
        assertTrue(Bisect.bisectLeft(new int[]{1,3,3,5,6,7,7,7,7,8}, -2, 0, 0) == 0);
        assertTrue(Bisect.bisectLeft(new int[]{1,3,3,5,6,7,7,7,7,8}, 8, 0, 10) == 9);
        assertTrue(Bisect.bisectLeft(new int[]{1,3,3,5,6,7,7,7,7,8}, 7, 0, 10) == 5);

        assertTrue(Bisect.bisectRight(new int[]{1,3,3,5,6,7,7,7,7,8}, 3, 0, 9) == 3);
        assertTrue(Bisect.bisectRight(new int[]{1,3,3,5,6,7,7,7,7,8}, 2, 4, 9) == 4);
        assertTrue(Bisect.bisectRight(new int[]{1,3,3,5,6,7,7,7,7,8}, 2, 0, 0) == 0);
        assertTrue(Bisect.bisectRight(new int[]{1,3,3,5,6,7,7,7,7,8}, -2, 0, 0) == 0);
        assertTrue(Bisect.bisectRight(new int[]{1,3,3,5,6,7,7,7,7,8}, 8, 0, 10) == 10);
        assertTrue(Bisect.bisectRight(new int[]{1,3,3,5,6,7,7,7,7,8}, 7, 0, 10) == 9);
    }

    @Test
    public void medium() {
        SplitEven lp = new SplitEven();
        System.out.println(lp.solve("1111000000"));
        assertTrue(false);
    }
//     @Test
    public void hard() {
        NQueen nq = new NQueen();
        nq.solveNQueens(9);
        assertTrue(true);
    }

    boolean listEqual(ListNode a, ListNode b) {
        while (a!= null && b!=null) {
            if (a.val != b.val) return false;
            a=a.next;
            b=b.next;
        }

        return a==null && b==null;
    }

    ListNode makeList(int[] arr) {
        if (arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode tmp = head;

        for (int i = 1; i < arr.length; i++) {
            tmp.next = new ListNode(arr[i]);
            tmp = tmp.next;
        }
        return head;
    }

    ListNode print(ListNode h) {
        while (h!=null) {
            System.out.print(h.val + " ");
            h=h.next;
        }
        System.out.println();
        return null;
    }
    @Test
    public void testConcurrency() {
//         Concurrency conc = new Concurrency();
//         try {
//             double n = conc.run();
//             System.out.println(n);
//             assertTrue(Double.compare(0.0, n) == 0);
//         } catch (InterruptedException  e) {
//         }
    }
}
