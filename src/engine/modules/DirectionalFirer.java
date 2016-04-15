package engine.modules;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.IPositionable;
import engine.definitions.SpriteDefinition;
import engine.sprite.ISprite;
import util.Coordinate;
import util.TimeDuration;


// TODO clean up this class
public class DirectionalFirer extends Firer {

    private static final int RADS_TO_DEGREES = 180;
    private IGame myGame;
    private IAttribute myWaitTime;
    private TimeDuration myLastFire;
    private IPositionable mySprite;
    private SpriteDefinition myProjectile;
    // should this (below) be an attribute?
    private double myAngle;
    private double myTimeSinceFire;

    public DirectionalFirer (IGame game,
                             SpriteDefinition projectile,
                             IPositionable sprite,
                             double waitTime,
                             double theta) {

        myGame = game;
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
        myLastFire = new TimeDuration(0);
        mySprite = sprite;
        myProjectile = projectile;
        myAngle = theta;
        myTimeSinceFire = 0;

    }

    @Override
    public void update (TimeDuration duration) {
        fire(duration);
    }

    private void fire (TimeDuration duration) {

        // if((duration.getMillis() - lastFire.getMillis()) >= myWaitTime.getValueProperty().get()){
        myTimeSinceFire += duration.getMillis();

        if (myTimeSinceFire >= myWaitTime.getValueProperty().get()) {
            myTimeSinceFire = 0;

            ISprite bullet = myProjectile.create();
            bullet.setLocation(new Coordinate(mySprite.getLocation().getX(),
                                              mySprite.getLocation().getY()));

            bullet.getMovementStrategy().setXVel(getXVel(myAngle));
            bullet.getMovementStrategy().setYVel(getYVel(myAngle));
            myGame.bufferedAdd(bullet);
            myLastFire = new TimeDuration(duration.getMillis());

        }
    }

    private double getXVel (double theta) {
        return myProjectile.getMovementDefinition().getSpeed() *
               Math.cos(theta * Math.PI / RADS_TO_DEGREES);
    }

    private double getYVel (double theta) {

        return myProjectile.getMovementDefinition().getSpeed() *
               Math.sin(theta * Math.PI / RADS_TO_DEGREES);
    }

}
