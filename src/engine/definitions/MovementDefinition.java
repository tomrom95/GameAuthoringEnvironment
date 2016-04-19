package engine.definitions;

import engine.Positionable;
import engine.modules.IMovementModule;


/**
 * This abstract class is the superclass for all movement module definitions, and handles all common
 * elements between them, such as speed
 * 
 *
 */
public abstract class MovementDefinition implements IDefinition {

    private double mySpeed;
    private double myAngle;

    public abstract IMovementModule create (Positionable parent);

    /*
     * TODO this should be moved to imovementmodule
     */
    public void setSpeed (double speed) {
        mySpeed = speed;
    }
    
    public void setAngle (double angle) {
    	myAngle = angle;
    }

    public double getSpeed () {
        return mySpeed;
    }
    
    public double getAngle () {
    	return myAngle;
    }

}
