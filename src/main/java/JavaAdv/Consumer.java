package JavaAdv;

public class Consumer implements Runnable {

    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
        new Thread(this).start();
    }

    @Override
    public void run() {

        for (int i = 0; true; i++) {
            var qnt = storage.getQnt();
            System.out.printf("Consumer get %s%n", qnt);
        }
    }
}
