package engine.definitions;

import engine.Positionable;
import engine.modules.ConstantMover;
import engine.modules.IMovementModule;

/**
 * This class represents a definition for a movement module that moves in a constant pattern
 *
 */
public class ConstantMoverDefinition extends MovementDefinition {


    private double myOrientation;

    @Override
    public IMovementModule create (Positionable parent) {
        return new ConstantMover(getSpeed(), myOrientation, parent);
    }

    public double getOrientation(){
    	return myOrientation;
    }
    
    public void setOrientaiton(double newOrientation){
    	myOrientation = newOrientation;
    }

}
