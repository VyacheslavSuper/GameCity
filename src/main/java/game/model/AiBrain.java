package game.model;

import game.ai.AiTackUtilities;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Semaphore;

@Data
@AllArgsConstructor
public class AiBrain extends Thread {
    private Person person;
    private Game game;
    private Semaphore mySemaphore;
    private Semaphore againstSemaphore;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                mySemaphore.acquire();
                if (!game.getFinished()) {
                    game.addWord(person, AiTackUtilities.aiChoseCity(person, game));
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                againstSemaphore.release();
            }
        }
    }
}
