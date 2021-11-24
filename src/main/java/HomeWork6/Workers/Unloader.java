package HomeWork6.Workers;

import HomeWork6.Cart;
import HomeWork6.Heap.MoneyHeap;

public class Unloader implements Worker {

    private static final int MAX_UNLOAD_PER_SEC = 3;
    private final Cart cart;

    public Unloader(Cart cart) {
        this.cart = cart;
        new Thread(this).start();
    }

    public void doWork() {
        try {
            do {
                cart.startUnloadMoney(MAX_UNLOAD_PER_SEC);
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
