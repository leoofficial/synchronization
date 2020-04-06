package dining_philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophersThreadRunner {

    public static void main(String[] args) {
        DiningTable diningTable = new DiningTable();
        PhilosopherRunnable[] philosopherThreads = new PhilosopherRunnable[5];
        for (int i = 0; i < 5; i++) philosopherThreads[i] = new PhilosopherRunnable(i, diningTable);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) threadPool.execute(philosopherThreads[i]);
        threadPool.shutdown();
    }
}
