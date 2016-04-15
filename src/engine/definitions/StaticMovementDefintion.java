package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.StaticMover;

/**
 * This class represents the definition for a stationary movement module
 *
 */
public class StaticMovementDefintion extends ConstantMoverDefinition {

    private static final double IMMOVABLE_SPEED = 0;
    
    private double myXVel = IMMOVABLE_SPEED;
    private double myYVel = IMMOVABLE_SPEED;

    @Override
    public IMovementModule create (IPositionable parent) {
        return new StaticMover(parent);
    }

    @Override
    public double getXVel () {
        return myXVel;
    }

    @Override
    public double getYVel () {
        return myYVel;
    }
}
