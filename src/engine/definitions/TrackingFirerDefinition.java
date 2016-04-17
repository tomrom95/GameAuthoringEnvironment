package engine.definitions;

import java.util.List;
import engine.IGame;
import engine.Positionable;
import engine.SpriteGroup;
import engine.modules.TrackingFirer;
import engine.sprite.SpriteType;

/**
 * This class represents the definition for a tracking firer module
 *
 */
public class TrackingFirerDefinition extends FirerDefinition {

    private SpriteGroup myTargets;
    private List<SpriteType> myTargetsType;
    private double myWaitTime;
    private IGame myGame;

    @Override
    public TrackingFirer create (Positionable parent) {
        myTargetsType = myTargets.getSpriteTypes();
        return new TrackingFirer(myTargetsType, myGame, myWaitTime, getProjectileDefinition(),
                                 parent);
    }

    public SpriteGroup getTargets () {
        return myTargets;
    }

    public void setWaitTime (double time) {
        myWaitTime = time;
    }

    public double getWaitTime () {
        return myWaitTime;
    }

    public void setGame (IGame game) {
        myGame = game;
    }

    public void setTargets (SpriteGroup targets) {
        myTargets = targets;
    }

    public IGame getGame () {
        return myGame;
    }

}
