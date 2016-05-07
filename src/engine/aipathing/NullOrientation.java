package engine.aipathing;

import util.Coordinate;


public class NullOrientation implements IOrientationFinder {

    @Override
    public double angleFromCoordinates (Coordinate start, Coordinate end) {
        return 0;
    }

    @Override
    public double angleFromDeltas (double deltaX, double deltaY) {
        return 0;
    }

}
