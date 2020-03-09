package game.ai;

import game.model.AiBrain;
import game.model.Game;
import lombok.Data;

import java.util.concurrent.Semaphore;

@Data
public class AiThread extends Thread {
    private AiBrain left;
    private AiBrain right;

    public AiThread(Game game) {
        int first = (int) (Math.random() * 2);
        Semaphore sLeft = new Semaphore(first);
        Semaphore sRight = new Semaphore(Math.abs(1 - first));
        left = new AiBrain(game.getLeftPerson(), game, sLeft, sRight);
        right = new AiBrain(game.getRightPerson(), game, sRight, sLeft);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            left.start();
            right.start();
            left.join();
            right.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
