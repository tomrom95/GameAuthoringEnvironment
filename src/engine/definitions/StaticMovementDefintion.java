package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.StaticMover;


public class StaticMovementDefintion extends ConstantMoverDefinition {

    private final double IMMOVABLE_SPEED = 0;
    private double myXVel = IMMOVABLE_SPEED;
    private double myYVel = IMMOVABLE_SPEED;

    @Override
    public IMovementModule create (IPositionable parent) {
        return new StaticMover(parent);
    }

    public double getXVel () {
        return myXVel;
    }

    public double getYVel () {
        return myYVel;
    }
}
