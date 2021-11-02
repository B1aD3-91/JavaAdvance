package JavaAdv2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Passenger implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private String name;

    public Passenger(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.printf("Pasenger %s start walking%n", name);
        try {
            TimeUnit.SECONDS.sleep((int) (Math.random() * 6 + 3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Passenger %s reached bus station%n", name);
        try {
            cyclicBarrier.await();
            System.out.println("Passenger departed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
