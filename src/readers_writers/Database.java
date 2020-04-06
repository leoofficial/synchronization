package readers_writers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Database<E> {

    E data = null;
    private int numberOfReaders = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition readableCondition = lock.newCondition();
    private final Condition writableCondition = lock.newCondition();

    public E read() {
        lock.lock();
        try {
            numberOfReaders++;
            if (numberOfReaders == 1) {
                System.out.println("Reader waits for readable condition");
                readableCondition.await();
            }
            numberOfReaders--;
            System.out.println("Reader reads  data " + data);
            if (numberOfReaders == 0) writableCondition.signal();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
        return data;
    }

    public void write(E data) {
        lock.lock();
        try {
            while (numberOfReaders > 1) {
                System.out.println("Writer waits for writable condition");
                writableCondition.await();
            }
            this.data = data;
            System.out.println("Writer writes data " + data);
            readableCondition.signal();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
