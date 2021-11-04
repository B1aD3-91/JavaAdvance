package HomeWork5_2.Customer;

import HomeWork5_2.McDuck.McDuck;
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
