package TaskThree;

import TaskTwo.Window;
import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Thread th = new Thread(() -> new Window(), "MyThread");
        th.start();
        //th.join();
    }
}
