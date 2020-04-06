package bounded_buffer;

import java.util.Random;

public class ConsumerRunnable implements Runnable {

    private Buffer<Integer> buffer;

    public ConsumerRunnable(Buffer<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            final int DELAY = 1024;
            while (true) {
                buffer.consume();
                Thread.sleep(new Random().nextInt(DELAY));
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
