package edu.geekhub.homework.transport.controllers;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.LocationService;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.track.controller.Track;
import edu.geekhub.homework.transport.Car;
import edu.geekhub.homework.transport.VehicleType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ScooterControllerTest {
    @Spy
    LocationService locationService;

    @Test
    @Tag("Correct")
    void Car_move() {
        TrackMap map = TrackMap.generate();
        Track track = new Track(map.getTrackElements());

        Point location = new Point(0, 0);
        Point newLocation = new Point(1, 0);
//        doReturn(newLocation).when(locationService).chooseNewRandomLocation(anyInt(), any(Point.class));

        Car car = new Car(location, track, VehicleType.SCOOTER);
        ScooterController scooterController = new ScooterController(car, track, map, locationService);

        scooterController.run();
    }
}
