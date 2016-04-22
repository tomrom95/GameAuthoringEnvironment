package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import util.Coordinate;


/**
 * Utility class holding various helpful geometry/math operations for the AI pathing
 * PathNode classes
 * 
 * @author jonathanim
 *
 */
public class PathNodeGeometry {
    private PathNodeGeometry () {
        // utility class so private constructor
    }

    /**
     * This method will return a list of points that sample the line
     * between two coordinates at the interval required to maintain
     * pixel level resolution
     * 
     * @param start Coordinate from which to build the line
     * @param end Coordinate to which to build the line
     * @return Coordinates of line in order from start to end
     */
    public static List<Coordinate> lineBetween (Coordinate start, Coordinate end) {
        double deltaX = end.getX() - start.getX();
        double deltaY = end.getY() - start.getY();
        double makePixelOrLess = proportionSoThatBothLessThanOne(deltaX, deltaY);
        deltaX = deltaX / makePixelOrLess;
        deltaY = deltaY / makePixelOrLess;
        double curX = start.getX();
        double curY = start.getY();
        List<Coordinate> toReturn = new ArrayList<>();
        while (curX <= end.getX()) {
            toReturn.add(new Coordinate(curX, curY));
            curX += deltaX;
            curY += deltaY;
        }
        return toReturn;
    }

    /**
     * See {@link #lineBetween(Coordinate, Coordinate)}
     * 
     * @param first
     * @param second
     * @return
     */
    public static List<Coordinate> lineBetween (IPathNode first, IPathNode second) {
        Coordinate start = first.getLocation();
        Coordinate end = second.getLocation();
        return lineBetween(start, end);
    }

    private static double proportionSoThatBothLessThanOne (double first, double second) {
        return Math.abs(first) > Math.abs(second) ? Math.abs(first) : Math.abs(second);
    }
}
