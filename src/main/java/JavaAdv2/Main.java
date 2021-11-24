package JavaAdv2;

import java.util.concurrent.CyclicBarrier;

public class Main {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("Bus started moving"));

    public static void main(String[] args) {
        new Passenger(cyclicBarrier, "A");
        new Passenger(cyclicBarrier, "B");
        new Passenger(cyclicBarrier, "C");
        new Passenger(cyclicBarrier, "D");
        new Passenger(cyclicBarrier, "E");
        new Passenger(cyclicBarrier, "F");
    }
}
