package banksimulator;
import java.util.*;


class Window extends Thread {
    Customer customer;
    boolean servingNow;
    BusinessType type;
    BankSimulator simulator;
    int id;

    public Window(int _id, BankSimulator _simulator, BusinessType _type) {
        id = _id;
        type = _type;
        simulator = _simulator;
        servingNow = false;
    }

    public void run() {
        System.out.println("Window "+id+" started");
        while (true) {
            Customer c = simulator.nextCustomer(type);
            if (c!=null) {
                System.out.format("%s serving %s\n", this, c);
                servingNow = true;
                try {
                    Thread.sleep(c.processTime*1000);
                } catch (InterruptedException e) {
                }
                servingNow = false;
                System.out.format("%s finished serving %s\n", this, c);
            }
        }
    }

    public String toString() {
        return String.format("[W%s-%s]", id, type);
    }
}
