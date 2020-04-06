package readers_writers;

import java.util.Random;

public class ReaderRunnable implements Runnable {

    private Database<Integer> database;

    public ReaderRunnable(Database<Integer> database) {
        this.database = database;
    }

    @Override
    public void run() {
        try {
            final int DELAY = 1024;
            while (true) {
                database.read();
                Thread.sleep(new Random().nextInt(DELAY));
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
