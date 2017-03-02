package banksimulator;
import java.util.*;


public class Customer implements Comparable<Customer>{
    int processTime=0; // seconds
    BusinessType type;
    int id;
    int arriveTime;

    public Customer(int _id, BusinessType _type, int _arriveTime) {
        arriveTime = _arriveTime;
        id = _id;
        type = _type;
        Random rand = new Random();
        int MIN_PRIORITY = BankSimulator.MIN_PRIORITY;
        int MAX_PRIORITY = BankSimulator.MAX_PRIORITY;
        switch (type) {
            case FAST:
                processTime = MIN_PRIORITY;
                break;
            case NORMAL:
            case VIP:
                processTime = rand.nextInt(MAX_PRIORITY-MIN_PRIORITY)+MIN_PRIORITY;
            default:
                assert false:type;
        }
        System.out.println(this +" entered bank");
    }

    public int compareTo(Customer o) {
        return arriveTime - o.arriveTime;
    }

    public String toString() {
        return String.format("[C%s-%s]", id, type);
    }
}

