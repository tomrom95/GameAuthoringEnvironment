package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.SpriteDefinition;
import engine.sprite.ISprite;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class creating a Firing module that will spawn the missiles specified in the authoring
 * environment and set their initial, and potentially constant, X and Y velocity to direct the
 * missile in a constant direction
 *
 * @author Timko
 *
 */
public class DirectionalFirer extends Firer {

    private Positionable mySprite;
    private SpriteDefinition myProjectile;
    private double myAngle;
    private TimeDuration myTimeSinceFire;

    public DirectionalFirer (IGame game,
                             SpriteDefinition projectile,
                             Positionable sprite,
                             double waitTime,
                             double theta) {
        super(game, sprite);
        setMyWaitTime(waitTime);
        myTimeSinceFire = new TimeDuration(0);
        mySprite = sprite;
        myProjectile = projectile;
        myAngle = theta;
        myTimeSinceFire.setToZero();

    }

    @Override
    public void update (TimeDuration duration) {

        super.update(duration);
        fire(duration);
    }

    private void fire (TimeDuration duration) {

        myTimeSinceFire.increase(duration);

        if (myTimeSinceFire.getSeconds() >= getMyWaitTime().getValueProperty().get()) {
            fire();

        }
    }

    protected void fire () {
        myTimeSinceFire.setToZero();
        ISprite bullet = myProjectile.create();
        bullet.setLocation(new Coordinate(mySprite.getLocation().getX(),
                                          mySprite.getLocation().getY()));
    
        bullet.getMovementStrategy().setOrientationFromTracker(myAngle);

        getGame().bufferedAdd(bullet);
        getFiredSprites().add(bullet);
    }

    protected void firerUpdate (TimeDuration t) {
        super.update(t);
    }

    protected SpriteDefinition getDefinition () {
        return myProjectile;
    }

    protected double getAngle () {
        return myAngle;
    }

    protected void setAngle (double theta) {
        myAngle = theta;
    }

}
