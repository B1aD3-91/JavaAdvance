package TaskFour.Mine;

import TaskFour.Exceptions.MineIsEmptyException;
import lombok.SneakyThrows;

public class Mine {

    private static final int MIN_DIGGING_GOLD = 1;
    private static final int MAX_DIGGING_GOLD = 3;

    private int gold = 1000;

    public Mine() {
    }

    public Mine(int goldSize) {
        gold = goldSize;
    }


    @SneakyThrows
    public synchronized int mineGold(int diggGold) {
        if (this.gold == 0) {
            throw new MineIsEmptyException();
        }
        if (this.gold <= diggGold) {
            var temp = this.gold;
            subtractGold(this.gold);
            return temp;
        }
        if (diggGold >= MIN_DIGGING_GOLD && diggGold <= MAX_DIGGING_GOLD) {
            this.gold -= diggGold;
            return diggGold;
        }
        System.out.println("Сударь, шахтер столько не сможет добыть! (укажите от 1 до 3х)");
        return 0;
    }


    public boolean mineIsEmpty() {
        return gold == 0;
    }

    private void subtractGold(int gold) {
        this.gold -= gold;
    }
}
