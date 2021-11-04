package HomeWork6.Workers;

import HomeWork6.Cart;
import HomeWork6.Heap.MoneyHeap;

public class Loader implements Worker {

    private static final int MAX_LOAD_PER_SEC = 2;
    private final Cart cart;

    public Loader(Cart cart) {
        this.cart = cart;
        new Thread(this).start();
    }

    public void doWork() {
        try {
            while (!MoneyHeap.isEmpty()) {
                cart.startLoadMoney(MAX_LOAD_PER_SEC);
            }
            System.out.println("Деньги закончились, нечего грузить!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doWork();
    }
}
