package game.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Semaphore;

@Data
@AllArgsConstructor
public class AiBrain {
    private Person person;
    private Game game;
    private Semaphore brainOne;
    private Semaphore brainTwo;
}
