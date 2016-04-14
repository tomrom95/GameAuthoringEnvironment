package engine.definitions;

import engine.IPositionable;
import engine.modules.ConstantMover;
import engine.modules.IMovementModule;

public class ConstantMoverDefinition extends MovementDefinition {

    private double myXVel;
    private double myYVel;
    
    @Override
    public IMovementModule create (IPositionable parent) {
        super.setSpeed(Math.sqrt(Math.pow(myXVel, 2) + Math.pow(myYVel, 2)));
        
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
