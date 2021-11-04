package HomeWork6.Workers;

import HomeWork6.Cart;
import HomeWork6.Heap.MoneyHeap;

public class Transporter implements Worker {

    private final Cart cart;

    public Transporter(Cart cart) {
        this.cart = cart;
        new Thread(this).start();
    }

    public void doWork() {
        try {
            do {
                cart.transportCart(5);
            } while (!MoneyHeap.isEmpty());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doWork();
    }
}
