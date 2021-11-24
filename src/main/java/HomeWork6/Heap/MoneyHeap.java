package HomeWork6.Heap;

public class MoneyHeap implements Heap {

    private static int money = 12;

    public MoneyHeap() {
    }

    public MoneyHeap(int money) {
        MoneyHeap.money = money;
    }

    public static boolean isEmpty() {
        return money == 0;
    }

    @Override
    public int getMoneyFromHeap(int getFromHeap) {
        if (getFromHeap <= money && getFromHeap > 0)
            money -= getFromHeap;
        return getFromHeap;
    }
}
