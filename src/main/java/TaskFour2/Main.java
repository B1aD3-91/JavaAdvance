package TaskFour2;

import TaskFour2.Customer.Customer;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        getCustomers();
    }

    private static void getCustomers() {
        Collections.nCopies(5, new Customer())
                .forEach(c -> new Thread(c).start());
    }
}
