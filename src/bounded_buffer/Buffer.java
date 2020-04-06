package bounded_buffer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer<E> {

    private int capacity;
    private final Queue<E> buffer = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmptyCondition = lock.newCondition();
    private final Condition notFullCondition = lock.newCondition();

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public Buffer() {
        this(1);
    }

    public void produce(E value) {
        lock.lock();
        try {
            if (buffer.size() == capacity) {
                System.out.println("Producer waits for notFull condition");
                notFullCondition.await();
            }
            buffer.offer(value);
            System.out.println("Producer produces " + value);
            notEmptyCondition.signal();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public E consume() {
        E value = null;
        lock.lock();
        try {
            if (buffer.size() == 0) {
                System.out.println("Consumer waits for notEmpty condition");
                notEmptyCondition.await();
            }
            value = buffer.poll();
            System.out.println("Consumer consumes " + value);
            notFullCondition.signal();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }
}
