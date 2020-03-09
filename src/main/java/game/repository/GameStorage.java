package game.repository;

import game.model.City;

import java.util.*;

public final class GameStorage {

    private static Map<String, List<City>> mapCities;
    private static Set<City> cities;


    public static synchronized void put(City city) {
        if (mapCities == null) {
            mapCities = new HashMap<>();
            cities = new HashSet<>();
        }
        if (!cities.contains(city)) {
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

    public static boolean isWordExist(City city) {
        return cities.contains(city);
    }

    public static void putAll(List<City> list) {
        if (mapCities == null) {
            mapCities = new HashMap<>();
            cities = new HashSet<>();
        }
        list.forEach(GameStorage::put);
    }

    public static List<City> getCitiesByKey(String key) {
        List<City> list = mapCities.get(key);
        if (list == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(list);
    }

    private static boolean checkCities(City city) {
        return mapCities.containsKey(city.getFirstSymbol());
    }

    public static List<City> getAllCities() {
        return new ArrayList<>(cities);
    }

    public static City getRandomCity() {
        return new ArrayList<>(cities).get((int) (Math.random() * cities.size()));
    }
}
