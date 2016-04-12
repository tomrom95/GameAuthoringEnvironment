package engine.definitions;

import engine.IPositionable;
import engine.modules.ConstantMover;
import engine.modules.IMovementModule;

public class ConstantMoverDefintion extends MovementDefinition {

    private double myXVel;
    private double myYVel;
    
    @Override
    public IMovementModule create (IPositionable parent) {
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
