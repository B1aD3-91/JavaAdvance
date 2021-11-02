package JavaAdv;

import java.util.concurrent.Semaphore;

public class Storage {

    private int qnt;

    private Semaphore producerSem = new Semaphore(1);
    private Semaphore consumerSem = new Semaphore(0);

    public int getQnt() {
        try {
            producerSem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int temp = qnt;
        qnt = 0;
        consumerSem.release();
        return temp;
    }

    public void setQnt(int qnt) {
        try {
            consumerSem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.qnt = qnt;
        producerSem.release();
    }

}
