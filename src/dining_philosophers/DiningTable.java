package dining_philosophers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningTable {

    enum StateEnum { THINKING, HUNGRY, EATING }

    private final Lock lock = new ReentrantLock();
    private final Condition[] self;
    private final StateEnum[] state;

    public DiningTable() {
        self = new Condition[5];
        state = new StateEnum[5];
        for (int i = 0; i < 5; i++) {
            self[i] = lock.newCondition();
            state[i] = StateEnum.THINKING;
        }
    }

    void pickUp(int i) {
        lock.lock();
        try {
            state[i] = StateEnum.HUNGRY;
            System.out.println("Philosopher " + i + " is hungry");
            test(i);
            if (state[i] != StateEnum.EATING) self[i].await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void putDown(int i) {
        lock.lock();
        try {
            state[i] = StateEnum.THINKING;
            System.out.println("Philosopher " + i + " is thinking");
            test((i + 4) % 5);
            test((i + 1) % 5);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void test(int i) throws InterruptedException {
        if (state[i] == StateEnum.HUNGRY &&
                state[(i + 4) % 5] != StateEnum.EATING &&
                state[(i + 1) % 5] != StateEnum.EATING) {
            state[i] = StateEnum.EATING;
            System.out.println("Philosopher " + i + " is eating");
            self[i].signal();
        }
    }
}
