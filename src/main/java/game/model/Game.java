package game.model;

import game.model.base.SwitchPerson;
import game.repository.GameStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static game.repository.GameStorage.getRandomCity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Game {
    private Timer timer;
    private int gameTime;
    private int numberCity;
    private Set<City> used;


    private ConcurrentHashMap<Integer, City> usedByLeftPerson;
    private ConcurrentHashMap<Integer, City> usedByRightPerson;
    private Person leftPerson;
    private Person rightPerson;
    private City currentCity;
    private Boolean finished;
    private Person winner;


    public static Game createGame(String nameLeftPerson, String nameRightPerson) {
        return new Game(null, 0, 0, null, null, null, new Person(nameLeftPerson, SwitchPerson.LEFT), new Person(nameRightPerson, SwitchPerson.RIGHT), null, false, null);
    }

    public City getCurrentWord() {
        return currentCity;
    }

    public void start() {
        used = new HashSet<>();
        usedByLeftPerson = new ConcurrentHashMap<>();
        usedByRightPerson = new ConcurrentHashMap<>();
        finished = false;
        numberCity = 0;
        gameTime = 0;
        timer = new Timer(1000, e -> gameTime++);
        timer.start();
        City city = getRandomCity();
        System.out.println(city);
        addWord(city);
    }

    public void stop() {
        finished = true;
        timer.stop();
    }

    public synchronized void addWord(Person person, City city) {
        switch (person.getSwitchPerson()) {
            case LEFT: {
                addLeftWord(city);
                break;
            }
            case RIGHT: {
                addRightWord(city);
                break;
            }
            default: {
                System.out.println("ERROR, who is it send City?");
            }
        }
    }

    private synchronized void addLeftWord(City city) {
        if (getFinished()) {
            return;
        }
        if (isWordExist(city) && addWord(city)) {
            usedByLeftPerson.put(numberCity, city);
        } else {
            System.out.println("lose " + city);
            finished = true;
            winner = rightPerson;
            System.out.println(winner);
        }
    }

    private synchronized void addRightWord(City city) {
        if (getFinished()) {
            return;
        }
        if (isWordExist(city) && addWord(city)) {
            usedByRightPerson.put(numberCity, city);
        } else {
            System.out.println("lose " + city);
            finished = true;
            winner = leftPerson;
            System.out.println(winner);
        }
    }

    private boolean addWord(City city) {
        boolean result = used.add(city);
        if (result) {
            currentCity = city;
            numberCity++;
        }
        return result;
    }

    private boolean isWordExist(City city) {
        return city != null && GameStorage.isWordExist(city);
    }
}
