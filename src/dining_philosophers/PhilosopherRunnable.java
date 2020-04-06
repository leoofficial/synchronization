package dining_philosophers;

import java.util.Random;

public class PhilosopherRunnable implements Runnable {

    private int id;
    private DiningTable diningTable;

    public PhilosopherRunnable(int id, DiningTable diningTable) {
        this.id = id;
        this.diningTable = diningTable;
    }

    @Override
    public void run() {
        try {
            final int DELAY = 1024;
            while (true) {
                diningTable.pickUp(id);
                diningTable.putDown(id);
                Thread.sleep(new Random().nextInt(DELAY));
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
