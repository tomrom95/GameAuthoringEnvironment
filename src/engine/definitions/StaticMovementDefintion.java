package engine.definitions;

import engine.Positionable;
import engine.modules.IMovementModule;
import engine.modules.StaticMover;

/**
 * This class represents the definition for a stationary movement module
 *
 */
public class StaticMovementDefintion extends ConstantMoverDefinition {

    private static final double IMMOVABLE_SPEED = 0;
    
    private double mySpeed = IMMOVABLE_SPEED;

    @Override
    public IMovementModule create (Positionable parent) {
    	setSpeed(mySpeed);
        return new StaticMover(parent);
    }

  
}
