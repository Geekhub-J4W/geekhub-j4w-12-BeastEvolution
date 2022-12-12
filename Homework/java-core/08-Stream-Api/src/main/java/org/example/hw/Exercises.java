package org.example.hw;

import org.example.hw.exceptions.NotFoundException;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercises {
    private final Cities citiesRepo;

    public Exercises(Cities citiesRepo) {
        this.citiesRepo = citiesRepo;
    }

    public Map<String, Long> getCountryCitiesCount() {
        // Find the number of cities of each country (use grouping)
        var cities = citiesRepo.getAllCities();
        return cities.values().stream()
                .collect(Collectors.groupingBy(City::getCountryCode,
                                Collectors.counting()
                        )
                );
    }

    public City mostPopulatedCity() {
        return citiesRepo.getAllCities()
            .values()
            .stream()
            .max(Comparator.comparingInt(City::getPopulation))
            .orElseThrow(() -> new NotFoundException("No cities found"));
    }

    public City minPopulatedCity() {
        return citiesRepo.getAllCities()
            .values()
            .stream()
            .min(Comparator.comparingInt(City::getPopulation))
            .orElseThrow(() -> new NotFoundException("No cities found"));
    }

    public String mostPopulatedCountry() {

        return citiesRepo.getAllCities()
            .values()
            .stream()
            .collect(Collectors.groupingBy(City::getCountryCode,
                Collectors.summingLong(City::getPopulation)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByKey())
            .orElseThrow(() -> new NotFoundException("No cities found"))
            .getKey();
    }

    public String minPopulatedCountry() {
        // Find the most populated city
        return null;
    }

    public Long totalPopulation() {
        // Find the most populated city
        return null;
    }

    public Map<String,Integer> populationOfEachCountry() {
        // Find the most populated city
        return null;
    }

    public Integer populationOfSpecificCountry(String countryCode) {
        // Find the most populated city
        return null;
    }

    public City specificCy(String city) {
        // Find the most populated city
        return null;
    }


}
