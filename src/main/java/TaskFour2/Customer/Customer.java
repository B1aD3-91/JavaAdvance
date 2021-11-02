package TaskFour2.Customer;

import TaskFour2.McDuck.McDuck;
import lombok.SneakyThrows;

public class Customer implements Runnable {

    private final McDuck mcDuck = new McDuck();

    @SneakyThrows
    public void buySomething() {
        mcDuck.freeCashier();
    }

    @Override
    public void run() {
        buySomething();
    }
}
