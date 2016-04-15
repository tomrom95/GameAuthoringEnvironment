package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;


/**
 * This abstract class is the superclass for all movement module definitions, and handles all common
 * elements between them, such as speed
 * 
 *
 */
public abstract class MovementDefinition implements IDefinition {

    private double mySpeed;

    public abstract IMovementModule create (IPositionable parent);

    /*
     * TODO this should be moved to imovementmodule
     */
    public void setSpeed (double speed) {
        mySpeed = speed;
    }

    public double getSpeed () {
        return mySpeed;
    }

}
