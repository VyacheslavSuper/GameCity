package game;


import game.model.City;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class UploadCities  {


    private List<City> getAllCitiesFromFile(String pathname) {
        List<City> requestCities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname), "windows-1251"))) {
            String city;
            do {
                city = br.readLine();
                if (city != null && !city.trim().isEmpty()) {
                    requestCities.add(new City(city.trim()));
                }
            } while (city != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestCities;
    }
}
