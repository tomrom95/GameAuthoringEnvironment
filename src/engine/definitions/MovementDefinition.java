package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;


public abstract class MovementDefinition implements IDefinition {

    private double mySpeed;

    public abstract IMovementModule create (IPositionable parent);

    /*
     * this should be moved to imovementmodule
     */
    public void setSpeed (double speed) {
        mySpeed = speed;
    }

    public double getSpeed () {
        return mySpeed;
    }
    
    

}
