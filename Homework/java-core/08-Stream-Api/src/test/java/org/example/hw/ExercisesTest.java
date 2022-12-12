package org.example.hw;

import org.example.hw.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExercisesTest {

    private Exercises exercises;

    @BeforeEach
    void setUp() {
        exercises = new Exercises(new CitiesImp());
    }

    @Test
    @Tag("correct work")
    @Tag(("mostPopulatedCity"))
    void get_most_populated_city() {
        City expectedCity = new City(1024, "Mumbai (Bombay)", "IND", 10500000);

        City actualCity = exercises.mostPopulatedCity();

        assertEquals(expectedCity, actualCity);
    }

    @Test
    @Tag("error")
    @Tag(("mostPopulatedCity"))
    void fail_find_most_populated_city() {
        exercises = new Exercises(new CitiesMock());

        NotFoundException thrown = assertThrows(NotFoundException.class, exercises::mostPopulatedCity);

        assertEquals("No cities found", thrown.getMessage());
    }

    @Test
    @Tag("correct work")
    @Tag(("minPopulatedCity"))
    void get_min_populated_city() {
        City expectedCity = new City(2912, "Adamstown", "PCN", 42);

        City actualCity = exercises.minPopulatedCity();

        assertEquals(expectedCity, actualCity);
    }

    @Test
    @Tag("error")
    @Tag(("minPopulatedCity"))
    void fail_find_min_populated_city() {
        exercises = new Exercises(new CitiesMock());

        NotFoundException thrown = assertThrows(NotFoundException.class, exercises::minPopulatedCity);

        assertEquals("No cities found", thrown.getMessage());
    }

}
