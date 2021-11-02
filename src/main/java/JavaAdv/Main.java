package JavaAdv;

public class Main {

    public static void main(String[] args) {
        Storage storage = new Storage();
        new Consumer(storage);
        new Producer(storage);
    }
}
