package HomeWork5_2.McDuck;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class McDuck {

    private static final int MIN_SERVICE_TIME = 3;
    private static final int MAX_SERVICE_TIME = 8;
    private final Random rnd = new Random();
    private final Object lock = new Object();

    @SneakyThrows
    public void freeCashier() {
        synchronized (lock) {
            if (closeTime()) {
               System.out.println("Time to close. Cashier is closing.");
              System.exit(0);
            }
            System.out.printf("Customer %s is service%n", Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(randomServiceTime());
        }
    }

    private int randomServiceTime() {
        return rnd.nextInt(MAX_SERVICE_TIME - MIN_SERVICE_TIME + 1) + MIN_SERVICE_TIME;
    }

    private boolean closeTime() {
        return ((rnd.nextInt(10) + 1)) % 3 == 0;
    }

    public synchronized boolean isFree() {
        return isFree();
    }
}
