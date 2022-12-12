package org.example.hw;

import java.util.HashMap;
import java.util.Map;

public class CitiesMock implements Cities{
    @Override
    public Map<Integer, City> getAllCities() {
        return new HashMap<>();
    }
}
