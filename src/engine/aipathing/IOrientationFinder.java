package engine.aipathing;

import util.Coordinate;


/**
 * This class will serve as the basis for helping make rotational behavior
 * of moving sprites modular
 *
 * Null and real versions of this object will be created which will allow
 * either orientation in the direction of travel, or orientation
 * indpendent of motion
 *
 * @author jonathanim
 *
 */
public interface IOrientationFinder {

    /**
     * Will calculate the angle heading of a sprite that
     * moved from the start to end coordinates
     *
     * @param start coordinate
     * @param end coordinate
     * @return the angle in degrees, 0 facing up
     */
    double angleFromCoordinates (Coordinate start, Coordinate end);

    /**
     * Will calculate the angle heading of a sprite that
     * moved the delta amount in each coordinate plane
     *
     * @param deltaX positive is motion towards the right
     * @param deltaY positive is motion downwards
     * @return the angle in degrees, 0 facing up
     */
    double angleFromDeltas (double deltaX, double deltaY);
}
