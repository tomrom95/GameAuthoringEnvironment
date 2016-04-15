package engine.definitions;

import engine.Positionable;
import engine.modules.ConstantMover;
import engine.modules.IMovementModule;

/**
 * This class represents a definition for a movement module that moves in a constant pattern
 *
 */
public class ConstantMoverDefinition extends MovementDefinition {

    private double myXVel;
    private double myYVel;

    @Override
    public IMovementModule create (Positionable parent) {
        setSpeed(Math.sqrt(Math.pow(myXVel, 2) + Math.pow(myYVel, 2)));
        return new ConstantMover(myXVel, myYVel, parent);
    }

    public void setXVel (double x) {
        myXVel = x;
    }

    public void setYVel (double y) {
        myYVel = y;
    }

    public double getXVel () {
        return myXVel;
    }

    public double getYVel () {
        return myYVel;
    }

}
