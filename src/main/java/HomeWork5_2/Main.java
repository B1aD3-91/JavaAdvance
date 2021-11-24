package HomeWork5_2;

import HomeWork5_2.Customer.Customer;
import HomeWork5_2.McDuck.McDuck;

import java.util.Collections;

public class Main {

    private static final McDuck mcDuck = new McDuck();

    public static void main(String[] args) {
        getCustomers();
    }

    private static void getCustomers() {
        Collections.nCopies(5, new Customer(mcDuck))
                .forEach(c -> new Thread(c).start());
    }
}
