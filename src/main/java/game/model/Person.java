package game.model;

import game.model.base.QualityStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import game.model.base.PersonStrategy;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private PersonStrategy strategy;
    private QualityStrategy quality;
    private List<City> usedByPerson;

    public Person(String name) {
        this(name, PersonStrategy.FIRST, QualityStrategy.EASY, new ArrayList<>());
    }
}
