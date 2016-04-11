package engine;

import util.Coordinate;

public interface IPositionable {

    Coordinate getLocation ();
    
    void setLocation (Coordinate location);
}
