package engine.definitions.moduledef;

import java.util.List;
import engine.IGame;
import engine.Positionable;
import engine.modules.IMovementModule;
import engine.modules.TrackingMover;
import engine.sprite.SpriteType;

/**
 * This class represents the definition for a tracking movement module
 *
 */
public class TrackingMoverDefinition extends MovementDefinition {

    private List<SpriteType> myTargets;
    private IGame myGame;
    private double mySpeed;

    @Override
    public IMovementModule create (Positionable parent) {
        return new TrackingMover(mySpeed,
                                 myGame,
                                 myTargets,
                                 parent);
        
    }

    public void setTargets (List<SpriteType> targets) {
        myTargets = targets;
    }

    public List<SpriteType> getTargets () {
        return myTargets;
    }

    public void setGame (IGame game) {
        myGame = game;
    }

    public IGame getGame () {
        return myGame;

    }

    @Override
    public double getSpeed () {
        return mySpeed;
    }

    @Override
    public void setSpeed (double speed) {
        mySpeed = speed;

    }

}
