package JavaAdv;

public class Producer implements Runnable {

    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            storage.setQnt(i);
            System.out.printf("Producer put %s%n", i);
        }
    }
}
