package HomeWork6;

import HomeWork6.Heap.Heap;
import HomeWork6.Heap.MoneyHeap;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Cart {

    private static final int MAX_LOAD_MONEY = 6;
    private final Semaphore loaderSem = new Semaphore(1);
    private final Semaphore unLoaderSem = new Semaphore(0);
    private final Semaphore transporterSem1 = new Semaphore(0);
    private final Semaphore transporterSem2 = new Semaphore(0);
    private final Heap heap = new MoneyHeap();
    private int money;

    public void startLoadMoney(int moneyPerSec) throws InterruptedException {
        loaderSem.acquire();

        System.out.println("Грузчик начал свою работу.");
        if (MAX_LOAD_MONEY % moneyPerSec == 0 && !isFull()) {
            while (!isFull()) {
                this.money += heap.getMoneyFromHeap(moneyPerSec);
                System.out.printf("В тележку загружено %s денег. Денег в тележке: %s%n", moneyPerSec, money);
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("Грузчик завершил свою работу.");
            transporterSem1.release();
        } else {
            System.out.println("Ой..Тележка заполнена, или вы попытались загрузить слишком много ):");
        }
    }

    public void transportCart(int sec) throws InterruptedException {
        transporterSem1.acquire();
        transport(sec);
        unLoaderSem.release();
        transporterSem2.acquire();
        transport(sec);
        loaderSem.release();
    }

    public void startUnloadMoney(int unloadPerSec) throws InterruptedException {
        unLoaderSem.acquire();

        System.out.println("Разгрузчик начал разгружать");
        if (money % unloadPerSec == 0) {
            while (!isEmpty()) {
                money -= unloadPerSec;
                System.out.printf("Из тележки выгрузили %s денег. Денег в тележке: %s%n", unloadPerSec, money);
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("Разгрузчик завершил разгрузку.");
            transporterSem2.release();
        } else {
            System.out.println("Ой...в тележке столько нет");
        }
    }

    private void transport(int sec) throws InterruptedException {
        System.out.println("Перевозчик повез тележку.");
        TimeUnit.SECONDS.sleep(sec);
        System.out.println("Перевозчик доставил тележку.");
    }

    public boolean isFull() {
        return money == MAX_LOAD_MONEY;
    }

    public boolean isEmpty() {
        return money == 0;
    }

    public int getMaxLoadMoneyToCart() {
        return MAX_LOAD_MONEY;
    }
}
