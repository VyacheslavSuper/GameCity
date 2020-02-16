package game.repository;

import game.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameStorage {

    private Map<String, List<City>> mapCities;
    private Set<City> cities;

    public synchronized void put(City city) {
        if(!cities.contains(city)) {
            cities.add(city);
            if (!checkCities(city)) {
                List<City> list = new ArrayList<>();
                list.add(city);
                mapCities.put(city.getFirstSymbol(), list);
            } else {
                mapCities.get(city.getFirstSymbol()).add(city);
            }
        }
    }

    public void putAll(List<City> list) {
        list.forEach(this::put);
        System.out.println(String.format("Upload %d cities", list.size()));
    }

    public List<City> getCitiesByKey(String key) {
        return new ArrayList<>(mapCities.get(key));
    }


    private boolean checkCities(City city){
        return mapCities.containsKey(city.getFirstSymbol());
    }
}
