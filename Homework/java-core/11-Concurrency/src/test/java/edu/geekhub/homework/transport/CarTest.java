package edu.geekhub.homework.transport;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.track.controller.Track;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarTest {

    @Test
    @Tag("Correct")
    void Car_move_on_track() {
        TrackMap trackMap = TrackMap.generate();
        Track track = new Track(trackMap.getTrackElements());
        Car car = new Car(new Point(0, 0), track, VehicleType.CAR);
        Car car1 = new Car(new Point(0, 1), track, VehicleType.CAR);

        car.move(new Point(0, 1));
    }

    @Test
    @Tag("Correct")
    void Spawn_car() {

    }

    @Test
    void Car_run() {

    }

}
