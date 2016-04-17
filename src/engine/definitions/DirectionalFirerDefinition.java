package engine.definitions;

import engine.IGame;
import engine.Positionable;
import engine.modules.DirectionalFirer;


/**
 * This class represents the definition of a directional firer, one that fired at a prespecified
 * angle (where 0 is to the right)
 *
 */
public class DirectionalFirerDefinition extends FirerDefinition {
    private double myWaitTime;
    private IGame myGame;
    private double myAngle;

    @Override
    public DirectionalFirer create (Positionable parent) {
        return new DirectionalFirer(myGame, getProjectileDefinition(), parent, myWaitTime, myAngle);
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

    public void setAngle (double theta) {
        myAngle = theta;
    }

    public double getAngle () {
        return myAngle;
    }
}
