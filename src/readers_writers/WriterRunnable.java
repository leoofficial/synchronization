package readers_writers;

import java.util.Random;

public class WriterRunnable implements Runnable {

    private Database<Integer> database;

    public WriterRunnable(Database<Integer> database) {
        this.database = database;
    }

    @Override
    public void run() {
        try {
            final int DELAY = 1024;
            int value = 1;
            while (true) {
                database.write(value++);
                Thread.sleep(new Random().nextInt(DELAY));
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
