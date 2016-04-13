package engine.definitions;

import java.util.List;
import engine.IGame;
import engine.IPositionable;
import engine.modules.TrackingFirer;
import engine.sprite.SpriteType;


public class TrackingFirerDefinition extends ModuleDefinition {

    private List<SpriteType> myTargets;
    private double myWaitTime;
    private IGame myGame;

    public void setTargets (List<SpriteType> targets) {
        myTargets = targets;
    }

    @Override
    public TrackingFirer create (IPositionable parent) {
        return new TrackingFirer(myTargets, myGame, myWaitTime, parent);

    }

    public List<SpriteType> getTargets () {
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

    public IGame getGame () {
        return myGame;

    }

}
