package game;


import game.model.City;
import game.repository.GameStorage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static game.constants.Constants.citiesPathName;


public class UploadCities extends Thread {
    public UploadCities() {
    }

    @Override
    public void run() {
        List<City> cities = getAllCitiesFromFile(citiesPathName);
        GameStorage.putAll(cities);
    }

    private List<City> getAllCitiesFromFile(String pathname) {
        List<City> requestCities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname), "windows-1251"))) {
            String city;
            do {
                city = br.readLine();
                if (city != null && !city.trim().isEmpty()) {
                    requestCities.add(City.createCity(city));
                }
            } while (city != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestCities;
    }
}
