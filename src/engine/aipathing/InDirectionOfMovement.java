package engine.aipathing;

import util.Coordinate;


public class InDirectionOfMovement implements IOrientationFinder {

    @Override
    public double angleFromCoordinates (Coordinate start, Coordinate end) {
        return angleFromDeltas(end.getX() - start.getX(), start.getY() - end.getY());
    }

    @Override
    public double angleFromDeltas (double deltaX, double deltaY) {
        return Math.toDegrees(Math.atan2(deltaY, deltaX));
    }

}
