package engine;

import util.Coordinate;
import util.TimeDuration;

public interface IMovementStrategy extends Affectable {

    /**
     * Makes the decision on where the Sprite should move next
     * @param coordinate - the current location of the Sprite
     * @return
     */
    
    Coordinate getNextLocation (Coordinate coordinate, TimeDuration timePassed);
}
