package me.derekzeng.code;
import java.util.*;
import java.io.*;
import java.lang.InterruptedException;
import java.util.concurrent.atomic.AtomicInteger;


public class Concurrency {

    int[] arr = new int[10];
    int current = 0;

    synchronized void add(int num) {
        while (current == arr.length) {
            try {
                wait();
            } catch(InterruptedException e) {
            }
        }
        arr[current++] = num;
//         Thread.currentThread().setPriority(Math.max(Thread.MIN_PRIORITY, 10-current));
        notify();
    }

    synchronized int remove() {
        while (current ==0) {
            try {
                wait();
            } catch(InterruptedException e) {
            }
        }
        current--;
//         Thread.currentThread().setPriority(Math.min(Thread.MAX_PRIORITY, current+1));
        notify();
        return arr[current];
    }

    static class Producer extends Thread {
        Concurrency conc;
        int id;
        Producer(int _id, Concurrency c) {
            id=_id;
            conc = c;
        }

        public void run() {
            while (true) {
                Random r = new Random();
                int num = r.nextInt(100);
                conc.add(num);
                System.out.println(id+ " added "+num);
            }
        }
    }

    static class Consumer extends Thread {
        Concurrency conc;
        int id;
        Consumer(int _id, Concurrency c) {
            id=_id;
            conc = c;
        }

        public void run() {
            while (true) {
                int num = conc.remove();
                System.out.println(id+" removed one. current: " + num);
            }
        }
    }

    public static void main(String[] args) {
        Concurrency conc = new Concurrency();
        for (int i=0;i<3;i++) {
            new Consumer(i, conc).start();
            new Producer(i, conc).start();
        }
    }
}
