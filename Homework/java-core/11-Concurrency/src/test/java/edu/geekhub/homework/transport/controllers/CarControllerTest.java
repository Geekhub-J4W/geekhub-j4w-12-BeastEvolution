package edu.geekhub.homework.transport.controllers;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.LocationService;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.track.controller.Track;
import edu.geekhub.homework.transport.Car;
import edu.geekhub.homework.transport.VehicleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    @Mock
    LocationService locationService;

    @Test
    void Crash_car() {
        TrackMap map = TrackMap.generate();
        Track track = new Track(map.getTrackElements());

        Point location = new Point(0, 0);
        Point newLocation = new Point(1, 0);
        doReturn(newLocation).when(locationService).chooseNewRandomLocation(anyInt(), any(Point.class));

        Car car = new Car(location, track, VehicleType.CAR);
        CarController carController = new CarController(car, track, map, locationService);
        car.setVehicleController(carController);
        Car car1 = new Car(newLocation, track, VehicleType.CAR);

        carController.run();
    }

    @Test
    void Finished_car() {
        TrackMap map = TrackMap.generate();
        Track track = new Track(map.getTrackElements());

        Point location = new Point(0, 0);
        Point finishedLocation = map.getFinishLocation().get(0);
        doReturn(finishedLocation).when(locationService).chooseNewRandomLocation(anyInt(), any(Point.class));

        Car car = new Car(location, track, VehicleType.CAR);
        CarController carController = new CarController(car, track, map, locationService);

        carController.run();
    }
}
