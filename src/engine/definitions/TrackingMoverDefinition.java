package engine.definitions;

import java.util.List;
import engine.IGame;
import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.TrackingMover;
import engine.sprite.SpriteType;


public class TrackingMoverDefinition extends MovementDefinition {

    private List<SpriteType> myTargets;
    private IGame myGame;
    private double mySpeed;

    @Override
    public IMovementModule create (IPositionable parent) {
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

    public double getSpeed () {
        return mySpeed;
    }

    public void setSpeed (double speed) {
        mySpeed = speed;

    }

}
