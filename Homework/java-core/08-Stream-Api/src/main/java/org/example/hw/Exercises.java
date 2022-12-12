package org.example.hw;

import org.example.hw.exceptions.NotFoundException;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercises {
    public static final String NO_CITIES_FOUND = "No cities found";
    private final Cities citiesRepo;

    public Exercises(Cities citiesRepo) {
        this.citiesRepo = citiesRepo;
    }

    public Map<String, Long> getCountryCitiesCount() {
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
            .orElseThrow(() -> new NotFoundException(NO_CITIES_FOUND));
    }

    public City minPopulatedCity() {
        return citiesRepo.getAllCities()
            .values()
            .stream()
            .min(Comparator.comparingInt(City::getPopulation))
            .orElseThrow(() -> new NotFoundException(NO_CITIES_FOUND));
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
            .orElseThrow(() -> new NotFoundException(NO_CITIES_FOUND))
            .getKey();
    }

    public String minPopulatedCountry() {
        return citiesRepo.getAllCities()
            .values()
            .stream()
            .collect(Collectors.groupingBy(City::getCountryCode,
                Collectors.summingLong(City::getPopulation)))
            .entrySet()
            .stream()
            .min(Map.Entry.comparingByKey())
            .orElseThrow(() -> new NotFoundException(NO_CITIES_FOUND))
            .getKey();
    }

    public Long totalPopulation() {
        return citiesRepo.getAllCities()
            .values()
            .stream()
            .mapToLong(City::getPopulation)
            .reduce(Long::sum).orElseThrow(() -> new NotFoundException(NO_CITIES_FOUND));
    }

    public Map<String,Integer> populationOfEachCountry() {
        return citiesRepo.getAllCities()
            .values()
            .stream()
            .collect(Collectors.groupingBy(City::getCountryCode,
                Collectors.summingInt(City::getPopulation)));
    }

    public Integer populationOfSpecificCountry(String countryCode) {
        return citiesRepo.getAllCities()
            .values()
            .stream()
            .filter(city -> city.getCountryCode().equals(countryCode))
            .mapToInt(City::getPopulation)
            .sum();
    }

    public City specificCity(String city) {
        return citiesRepo.getAllCities()
            .values()
            .stream()
            .filter(existentCity -> city.equals(existentCity.getName()))
            .findAny()
            .orElseThrow(() -> new NotFoundException("Not found " + city + " in this structure"));
    }


}
