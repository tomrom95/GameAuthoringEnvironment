package engine.modules;

import java.util.List;
import util.Coordinate;


/**
 * This interface represents a module of a sprite that handles its movement and positioning
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IMovementModule extends IModule {

    void setPath (List<Coordinate> newPath);

    List<Coordinate> getPath ();
    
    void setOrientation(double newAngle);
    
    void setSpeed(double newSpeed);


    /**
     * @return angle by which the render should rotate the sprite before display
     */
    double getOrientation ();
    
    
    double getSpeed();



}
