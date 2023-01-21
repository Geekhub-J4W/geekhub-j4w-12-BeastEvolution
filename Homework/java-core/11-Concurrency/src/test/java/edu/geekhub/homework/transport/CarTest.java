package edu.geekhub.homework.transport;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.TrackMap;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void Car_can_finish() {
        TrackMap trackMap = TrackMap.generate();
//        trackMap.getTrackElements().keySet().stream().forEach(System.out::println);
//        System.out.println();
//        System.out.println("Start: ");
        System.out.println(trackMap.getFinishLocation().get(5));
        System.out.println();
        Car car = new Car(trackMap.getFinishLocation().get(5), trackMap);
        car.run();
    }

    @Test
    void Car_run() {
        TrackMap trackMap = TrackMap.generate();
        Car car = new Car(new Point(0,0), trackMap);
        car.run();
    }

}
