package HomeWork5_1.BarrackService;

import HomeWork5_1.Mine.Mine;
import HomeWork5_1.Model.Miner;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BarrackImp implements Barrack {

    private static final long SCHEDULER_DELAY = 5000L;
    private static final long SCHEDULER_PERIOD = 10000L;
    private static final String WORKER_NAME_PREFIX = "Worker#";

    private final Mine mine = new Mine();

    private final Timer timer = new Timer();
    private List<Miner> miners = new ArrayList<>();

    public BarrackImp() {
        miners.add(new Miner("Worker#1", mine));
        miners.add(new Miner("Worker#2", mine));
        miners.add(new Miner("Worker#3", mine));
    }

    @SneakyThrows
    public void getStarted() {
        miners.forEach(x -> new Thread(x).start());
        timer.schedule(new TimerTask() {
            public void run() {
                createMiner().doWork();
            }
        }, SCHEDULER_DELAY, SCHEDULER_PERIOD);

        while (!mine.mineIsEmpty()) {
            timer.cancel();
            Thread.sleep(1000);
        }
    }

    private Miner createMiner() {
        var miner = new Miner(WORKER_NAME_PREFIX + (miners.size() + 1), mine);
        miners.add(miner);
        return miner;
    }
}

