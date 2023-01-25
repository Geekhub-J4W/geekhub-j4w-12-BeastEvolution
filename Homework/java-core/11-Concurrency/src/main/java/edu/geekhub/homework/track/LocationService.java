package edu.geekhub.homework.track;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.transport.util.Direction;

public class LocationService {
    public Point chooseNewRandomLocation(int moveDistance, Point location) {
        Direction direction = Direction.randomDirection();
        return location.createRespectToThis(direction.getX(moveDistance), direction.getY(moveDistance));
    }
}
