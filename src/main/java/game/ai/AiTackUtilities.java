package game.ai;

import game.model.City;
import game.model.Game;
import game.model.Person;
import game.model.base.PersonStrategy;
import game.repository.GameStorage;

import java.util.Collections;
import java.util.List;

public class AiTackUtilities {

    public static City aiChoseCity(Person person, Game game) {
        if (person.getStrategy().equals(PersonStrategy.FIRST)) {
            return getCityByStrategy(game);
        }
        return getCityByStrategy(person, game);
    }

    private static City getCityByStrategy(Game game) {
        City current = game.getCurrentWord();
        List<City> list = GameStorage.getCitiesByKey(current.getLastSymbol());
        return getCityByRandom(list);
    }

    private static City getCityByStrategy(Person person, Game game) {
        City current = game.getCurrentWord();
        List<City> knowledge = person.getCitiesByKey(current.getLastSymbol());
        knowledge.removeAll(game.getUsed());
        if (person.getStrategy().equals(PersonStrategy.SECOND)) {
            return getCityByRandom(knowledge);
        } else {
            return getCityByCount(knowledge);
        }
    }

    private static City getCityByCount(List<City> list) {
        if (!isGoodList(list)) {
            return null;
        }
        City selected = list.get(0);
        int count = GameStorage.getCitiesByKey(selected.getLastSymbol()).size();
        for (City city : list) {
            if (GameStorage.getCitiesByKey(city.getLastSymbol()).size() < count) {
                selected = city;
                count = GameStorage.getCitiesByKey(city.getLastSymbol()).size();
            }
        }
        return selected;
    }

    private static boolean isGoodList(List<City> list) {
        return list != null && !list.isEmpty();
    }

    private static City getCityByRandom(List<City> list) {
        if (isGoodList(list)) {
            Collections.shuffle(list);
            return list.get((int) (Math.random() * list.size()));
        } else {
            return null;
        }
    }
}
