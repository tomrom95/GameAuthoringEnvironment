package engine;

import java.util.List;
import util.Coordinate;


/**
 * This interface represents the functionality of an object that has a position associated with it
 *
 */
public interface Positionable {

    /**
     * @return the coordinate position of this object
     */
    Coordinate getLocation ();

    /**
     * @param coordinate location to set to
     */
    void setLocation (Coordinate coordinate);

    List<Coordinate> getPath ();
}
