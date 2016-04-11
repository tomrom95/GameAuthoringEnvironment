package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;


public abstract class MovementDefinition implements IDefinition {

    private double mySpeed;

    public abstract IMovementModule create (IPositionable parent);

    public void setSpeed (double speed) {
        mySpeed = speed;
    }

    protected double getSpeed () {
        return mySpeed;
    }
}
