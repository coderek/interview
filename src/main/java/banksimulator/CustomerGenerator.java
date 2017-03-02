package banksimulator;
import java.util.*;
import java.time.*;


/**
 * For generating customers
 * Lazily generate infinite number of customers
 */
class CustomerGenerator extends Thread {
    Random rand;
    BankQueue queue;
    int counter = 0;

    CustomerGenerator(BankQueue _queue) {
        queue = _queue;
        rand = new Random();
    }

    boolean hasNext() {
        return true;
    }

    Customer next() {
        // VIP客户：普通客户：快速客户  =  1 ：6 ：3
        double n = rand.nextDouble();
        int nextId = counter++;
        LocalTime now = LocalTime.now();
        int seconds = now.getSecond();
        if (n<0.1) {
            return new Customer(nextId, BusinessType.VIP, seconds);
        }
        if (n<0.7) {
            return new Customer(nextId, BusinessType.NORMAL, seconds);
        }
        return new Customer(nextId, BusinessType.FAST, seconds);
    }

    public void run() {
        while (true) {
            // schedule generator to run every 1 sec
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            queue.offer(next());
        }
    }
}
