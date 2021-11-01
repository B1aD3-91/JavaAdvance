package TaskFour.Model;

import TaskFour.Exceptions.MineIsEmptyException;
import TaskFour.Mine.Mine;
import lombok.SneakyThrows;

import java.util.Random;

public class Miner implements Runnable {

    private final Mine mine;
    private final Random rnd = new Random();
    private String name;
    private int hasGolds;

    public Miner(String minerName, Mine mine) {
        this.name = minerName;
        this.mine = mine;
        System.out.printf("Created worker: %s%n", minerName);
    }

    public int getHasGolds() {
        return hasGolds;
    }

    public void addGoldToPersonalCart(int gold) {
        this.hasGolds += gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SneakyThrows
    public void doWork() {
        try {
            while (true) {
                var minedGold = mine.mineGold(rnd.nextInt(3) + 1);
                addGoldToPersonalCart(minedGold);
                System.out.printf("Miner %s - mined gold: %s%n", name, minedGold);
                System.out.printf("Miner %s - has gold - %s%n", name, hasGolds);
                //Thread.sleep(1000);
            }
        } catch (MineIsEmptyException ex) {
            System.out.println("Ваша шахта пуста сударь!");
        }
    }

    @Override
    public void run() {
        doWork();
    }
}
