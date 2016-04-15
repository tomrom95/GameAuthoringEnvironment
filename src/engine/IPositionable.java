package engine;

import java.util.List;
import util.Coordinate;


public interface IPositionable {

    Coordinate getLocation ();

    void setLocation (Coordinate location);

    List<Coordinate> getPath ();
}
