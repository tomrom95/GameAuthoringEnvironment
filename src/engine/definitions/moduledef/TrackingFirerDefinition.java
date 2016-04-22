package engine.definitions.moduledef;

import java.util.List;
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

    @Override
    public TrackingFirer create (Positionable parent) {
        myTargetsType = myTargets.getSpriteTypes();
        TrackingFirer myNewFirer =  new TrackingFirer(myTargetsType, myWaitTime, getProjectileDefinition(),
                                 parent);
        setSuperVariables(myNewFirer);
        return myNewFirer;
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

    

    public void setTargets (SpriteGroup targets) {
        myTargets = targets;
    }

  

}
