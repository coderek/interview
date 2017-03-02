package banksimulator;
import java.util.*;


public class BankQueue {
    // maintain pqueue for all three types
    PriorityQueue<Customer> fast = new PriorityQueue<>();
    PriorityQueue<Customer> normal = new PriorityQueue<>();
    PriorityQueue<Customer> vip = new PriorityQueue<>();

    PriorityQueue[] queues = {vip, normal, fast};


    void offer(Customer customer) {
        assert customer.type.ordinal() >= 0 && customer.type.ordinal() < 3;
        queues[customer.type.ordinal()].offer(customer);
    }

    Customer poll(BusinessType windowType, boolean normalIsFull) {
        assert windowType.ordinal() >= 0 && windowType.ordinal() < 3;

        PriorityQueue<Customer> queue = queues[windowType.ordinal()];
        if (!queue.isEmpty()) {
            return queue.poll();
        }
        // no customer matching the window type
        // if window type is VIP or Fast, it can take any normal customer
        if (windowType == BusinessType.FAST || windowType == BusinessType.VIP) {
            if (normalIsFull) {
                return normal.poll();
            }
        }

        return null;
    }
}
