package game.model;

import game.model.base.PersonStrategy;
import game.model.base.SwitchPerson;
import game.repository.GameStorage;
import lombok.Data;

import java.util.*;

@Data
public class Person {
    private String name;
    private SwitchPerson switchPerson;
    private PersonStrategy strategy;
    private int quality;
    private List<City> knowledge;
    private Map<String, List<City>> mapKnowledge;

    public Person(String name, SwitchPerson switchPerson) {
        this(name, switchPerson, PersonStrategy.FIRST, 100);
    }

    public Person(String name, SwitchPerson switchPerson, PersonStrategy strategy, int quality) {
        this.name = name;
        this.switchPerson = switchPerson;
        this.strategy = strategy;
        clearKnowledge();
        setQuality(quality);
    }

    public void setQuality(int quality) {
        this.quality = quality;
        putKnowledge();
    }

    private void clearKnowledge() {
        knowledge = new ArrayList<>();
        mapKnowledge = new HashMap<>();
    }

    private void putKnowledge() {
        clearKnowledge();
        List<City> list = GameStorage.getAllCities();
        Collections.shuffle(list);
        for (int i = 0; i < (list.size() * quality / 100); i++) {
            City temp = list.get(i);
            knowledge.add(temp);
            List<City> bySymbol = mapKnowledge.get(temp.getFirstSymbol());
            if (bySymbol == null) {
                bySymbol = new ArrayList<>();
            }
            bySymbol.add(temp);
            mapKnowledge.put(temp.getFirstSymbol(), bySymbol);
        }
    }

    public List<City> getCitiesByKey(String key) {
        List<City> list = mapKnowledge.get(key);
        if (list == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
