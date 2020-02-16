package game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game {
    private Timer timer;
    private Set<City> used;
    private Person leftPerson;
    private Person rightPerson;

    public static Game createGame(String nameLeftPerson, String nameRightPerson) {
        return new Game(null, new HashSet<>(), new Person(nameLeftPerson), new Person(nameRightPerson));
    }
}
