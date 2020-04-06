package bounded_buffer;

import java.util.Random;

public class ProducerRunnable implements Runnable {

    private Buffer<Integer> buffer;

    public ProducerRunnable(Buffer<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            final int DELAY = 1024;
            int value = 1;
            while (true) {
                buffer.produce(value++);
                Thread.sleep(new Random().nextInt(DELAY));
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
