package engine.definitions;

import java.util.List;
import engine.IGame;
import engine.IPositionable;
import engine.modules.DirectionalFirer;
import engine.sprite.SpriteType;


public class DirectionalFirerDefinition extends ModuleDefinition {
    private double myWaitTime;
    private IGame myGame;
    private SpriteDefinition myProjectile;
    private double myAngle;

    @Override
    public DirectionalFirer create (IPositionable parent) {
        return new DirectionalFirer(myGame, myProjectile, parent, myWaitTime, myAngle);

    }

    public void setProjectileDefinition (SpriteDefinition projectile) {
        myProjectile = projectile;
    }

    public SpriteDefinition getProjectileDefinition () {
        return myProjectile;
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
