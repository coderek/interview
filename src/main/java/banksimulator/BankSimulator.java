package banksimulator;
import java.util.*;


public class BankSimulator {

    public static final int MAX_PRIORITY = 20;
    public static final int MIN_PRIORITY = 2;

    Window[] windows = new Window[6];
    CustomerGenerator customerGenerator;
    BankQueue queue = new BankQueue();


    void start() {
        initializeWidnows();
        initializeCustomerGenerator();

        for (Window win: windows) {
            win.start();
        }
    }

    synchronized Customer nextCustomer(BusinessType windowType) {
        boolean normalIsFull = Arrays.stream(windows).filter(
                w->w.type==BusinessType.NORMAL && !w.servingNow).count()==4;
        return queue.poll(windowType, normalIsFull);
    }

    void initializeCustomerGenerator() {
        customerGenerator = new CustomerGenerator(queue);
        customerGenerator.start();
    }

    void initializeWidnows() {
        for (int i=0;i<4;i++) {
            windows[i] = new Window(i, this, BusinessType.NORMAL);
        }
        windows[4] = new Window(4, this, BusinessType.FAST);
        windows[5] = new Window(5, this, BusinessType.VIP);
    }

    public static void main(String[] args) {
        BankSimulator simulator = new BankSimulator();
        simulator.start();
    }
}
