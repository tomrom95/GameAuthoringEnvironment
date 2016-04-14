package engine.definitions;

import java.util.List;
import engine.IGame;
import engine.IPositionable;
import engine.SpriteGroup;
import engine.modules.TrackingFirer;
import engine.sprite.SpriteType;


public class TrackingFirerDefinition extends FirerDefinition {

    private SpriteGroup myTargets;
    private List<SpriteType> myTargetsType;
    private double myWaitTime;
    private IGame myGame;


    @Override
    public TrackingFirer create (IPositionable parent) {
        myTargetsType = myTargets.getSpriteTypes();
        return new TrackingFirer(myTargetsType, myGame, myWaitTime, myProjectile, parent);

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
