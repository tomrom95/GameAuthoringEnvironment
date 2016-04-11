package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.StaticMover;

public class StaticMoverDefinition extends MovementDefinition {

    @Override
    public IMovementModule create (IPositionable parent) {
        return new StaticMover(parent);
    }

}
