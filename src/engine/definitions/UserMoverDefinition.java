package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.UserMover;

public class UserMoverDefinition extends MovementDefinition {

   
    private KeyControlDefinition myKeys;
    
    @Override
    public IMovementModule create (IPositionable parent) {
        return new UserMover(getSpeed(), myKeys.create(), parent);
    }

   

}
