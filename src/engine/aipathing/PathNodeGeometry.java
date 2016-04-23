package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import util.ArrayPosition;
import util.Coordinate;


/**
 * Utility class holding various helpful geometry/math operations for the AI pathing
 * PathNode classes
 * 
 * @author jonathanim
 *
 */
public class PathNodeGeometry {
    private static final int INT_TWO = 2;

    private PathNodeGeometry () {
        // utility class so private constructor
    }


    public static List<Coordinate> lineRounder (List<Coordinate> lineToRound) {
        List<Coordinate> toReturn = new ArrayList<>();
        for (Coordinate coord : lineToRound) {
            toReturn.add(new Coordinate(Math.round(coord.getX()), Math.round(coord.getY())));
        }
        return toReturn;
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

    public static double distance (ArrayPosition pos1, ArrayPosition pos2) {
        double x1 = pos1.getX();
        double x2 = pos2.getX();
        double y1 = pos1.getY();
        double y2 = pos2.getY();
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }
    
    public static ArrayPosition midPoint (ArrayPosition pos1, ArrayPosition pos2) {
        return new ArrayPosition((pos1.getX() + pos2.getX()) / INT_TWO,
                                 (pos1.getY() + pos2.getY()) / INT_TWO);
    }
}
