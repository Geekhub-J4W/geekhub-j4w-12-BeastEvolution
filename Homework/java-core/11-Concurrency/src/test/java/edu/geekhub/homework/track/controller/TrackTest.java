package edu.geekhub.homework.track.controller;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.transport.interfaces.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackTest {
    @Mock
    Set<Vehicle> locationContent;
    @Mock
    Map<Point, Set<Vehicle>> trackLocations;
    @Mock
    Vehicle vehicle;

    Track sut;

    @BeforeEach
    void setUp() {
        sut = new Track(trackLocations);
    }

    @Test
    @Tag("Correct")
    void Set_vehicle_on_map_location() {
        Point location = new Point(0, 0);
        when(trackLocations.containsKey(location))
            .thenReturn(true);
        when(trackLocations.get(location))
            .thenReturn(locationContent);
        sut.setVehicleOnMapLocation(vehicle, location);

        verify(trackLocations).get(location);
        verify(locationContent).add(vehicle);
    }

    @Test
    @Tag("Exception")
    void Invalid_to_set_vehicle_on_nonexistent_location() {
        Point location = new Point(0, 0);
        when(trackLocations.containsKey(location))
            .thenReturn(false);

        assertThatThrownBy(() -> sut.setVehicleOnMapLocation(vehicle, location))
            .isInstanceOf(LocationNonexistentException.class)
            .hasMessage(String.format("Location %s not exist on map %s", location, trackLocations));
    }

    @Test
    @Tag("Correct")
    void Delete_vehicle_from_map_location() {
        Point location = new Point(0, 0);
        when(trackLocations.containsKey(location))
            .thenReturn(true);
        when(trackLocations.get(location)).
            thenReturn(locationContent);

        sut.deleteVehicleFromLocation(vehicle, location);

        verify(locationContent).remove(vehicle);
    }

    @Test
    @Tag("Correct")
    void Return_true_if_delete_vehicle_from_map_location() {
        Point location = new Point(0, 0);
        when(trackLocations.containsKey(location))
            .thenReturn(true);
        when(trackLocations.get(location)).
            thenReturn(locationContent);
        when(locationContent.remove(vehicle)).thenReturn(true);

        boolean actualResult = sut.deleteVehicleFromLocation(vehicle, location);

        assertThat(actualResult)
            .isTrue();
    }

    @Test
    @Tag("Correct")
    void Return_false_if_delete_vehicle_what_not_on_location() {
        Point location = new Point(0, 0);
        when(trackLocations.containsKey(location))
            .thenReturn(true);
        when(trackLocations.get(location)).
            thenReturn(locationContent);
        when(locationContent.remove(vehicle)).thenReturn(false);

        boolean actualResult = sut.deleteVehicleFromLocation(vehicle, location);

        assertThat(actualResult)
            .isFalse();
    }

    @Test
    @Tag("Exception")
    void Invalid_to_delete_vehicle_from_nonexistent_location() {
        Point location = new Point(0, 0);
        when(trackLocations.containsKey(location))
            .thenReturn(false);

        assertThatThrownBy(() ->sut.deleteVehicleFromLocation(vehicle, location))
            .isInstanceOf(LocationNonexistentException.class)
            .hasMessage(String.format("Location %s not exist on map %s", location, trackLocations));
    }

    @Test
    @Tag("Correct")
    void Delete_all_vehicles_from_location() {
        Point location = new Point(0, 0);
        when(trackLocations.get(location))
            .thenReturn(locationContent);

        sut.deleteAllVehicleFromLocation(location);

        verify(locationContent).clear();
    }

    @Test
    @Tag("Correct")
    void Get_location_content() {
        Point location = new Point(0, 0);
        when(trackLocations.get(location))
            .thenReturn(locationContent);

        Set<Vehicle> actualResult = sut.getLocationContent(location);

        assertThat(actualResult).isEqualTo(locationContent);
    }

//    @Test
//    @Tag("Correct")
//    void Change_vehicle_location() {
//        Point oldLocation = new Point(0, 0);
//        Point newLocation = new Point(0, 1);
//        Map<Point, Set<Vehicle>> trackLocations = new HashMap<>();
//        trackLocations.put(oldLocation, new HashSet<>());
//        trackLocations.put(newLocation, new HashSet<>());
//        sut = new Track(trackLocations);
//
//        sut.changeVehicleLocation(vehicle, oldLocation, newLocation);
//        assertThat()
//    }
}
