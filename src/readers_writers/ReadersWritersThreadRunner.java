package readers_writers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadersWritersThreadRunner {

    public static void main(String[] args) {
        Database<Integer> database = new Database<>();
        ReaderRunnable readerThread = new ReaderRunnable(database);
        WriterRunnable writerThread = new WriterRunnable(database);
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(readerThread);
        threadPool.execute(writerThread);
        threadPool.shutdown();
    }
}
