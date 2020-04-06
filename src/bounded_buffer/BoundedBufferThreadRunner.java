package bounded_buffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoundedBufferThreadRunner {

    public static void main(String[] args) {
        Buffer<Integer> buffer = new Buffer<>();
        ProducerRunnable producerThread = new ProducerRunnable(buffer);
        ConsumerRunnable consumerThread = new ConsumerRunnable(buffer);
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(producerThread);
        threadPool.execute(consumerThread);
        threadPool.shutdown();
    }
}
