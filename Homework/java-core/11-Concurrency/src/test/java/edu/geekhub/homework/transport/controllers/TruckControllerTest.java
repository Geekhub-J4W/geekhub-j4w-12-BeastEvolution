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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TruckControllerTest {
    @Mock
    LocationService locationService;
    @Mock
    LocationService locationService1;

    @Test
    @Tag("Correct")
    void Deadlock_happened_when_truck_controller_run() {
        TrackMap trackMap = TrackMap.generate();
        Track track = new Track(trackMap.getTrackElements());

        Point spawnLocation = new Point(0, 0);
        Car car = new Car(spawnLocation, track, VehicleType.TRUCK);
        track.setVehicleOnMapLocation(car, spawnLocation);

        Point spawnLocation1 = new Point(0, 1);
        Car car1 = new Car(spawnLocation1, track, VehicleType.TRUCK);
        track.setVehicleOnMapLocation(car1, spawnLocation1);

        TruckController controller = new TruckController(car, track, trackMap, locationService);
        car.setVehicleController(controller);
        TruckController controller1 = new TruckController(car1, track, trackMap, locationService1);
        car1.setVehicleController(controller1);

        Point carNewLocation = new Point(0, 1);
        Point carNewLocation1 = new Point(0, 0);

        doReturn(carNewLocation).when(locationService).chooseNewRandomLocation(anyInt(), any(Point.class));
        doReturn(carNewLocation1).when(locationService1).chooseNewRandomLocation(anyInt(), any(Point.class));

        controller.run();
        controller1.run();
    }


    @Test
    @Tag("Correct")
    void Truck_move_to_outroad_happened_when_truck_controller_run() {
        TrackMap trackMap = TrackMap.generate();
        Track track = new Track(trackMap.getTrackElements());

        Point spawnLocation = new Point(0, 0);
        Car car = new Car(spawnLocation, track, VehicleType.TRUCK);
        track.setVehicleOnMapLocation(car, spawnLocation);

        TruckController controller = new TruckController(car, track, trackMap, locationService);
        car.setVehicleController(controller);

        Point carNewLocation = new Point(100, 100);
        doReturn(carNewLocation).when(locationService).chooseNewRandomLocation(anyInt(), any(Point.class));

        controller.run();
    }

    @Test
    @Tag("Correct")
    void Truck_truck_controller_real_run() {
        while (true) {
            TrackMap trackMap = TrackMap.generate();
            Track track = new Track(trackMap.getTrackElements());

            Point spawnLocation = new Point(0, 0);
            Car car = new Car(spawnLocation, track, VehicleType.TRUCK);
            track.setVehicleOnMapLocation(car, spawnLocation);

            TruckController controller = new TruckController(car, track, trackMap, new LocationService());
            car.setVehicleController(controller);

            controller.run();
        }
    }
}
