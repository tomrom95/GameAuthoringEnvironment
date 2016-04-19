package engine.modules;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
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
	

    private IGame myGame;
    private IAttribute myWaitTime;
    private Positionable mySprite;
    private SpriteDefinition myProjectile;
    private double myAngle;
    private TimeDuration myTimeSinceFire;

    public DirectionalFirer (IGame game,
                             SpriteDefinition projectile,
                             Positionable sprite,
                             double waitTime,
                             double theta) {

        myGame = game;
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
        mySprite = sprite;
        myProjectile = projectile;
        myAngle = theta;
        myTimeSinceFire.setToZero();;

    }

    @Override
    public void update (TimeDuration duration) {
        fire(duration);
    }

    private void fire (TimeDuration duration) {

        myTimeSinceFire.increase(duration);

        if (myTimeSinceFire.getSeconds() >= myWaitTime.getValueProperty().get()) {
            myTimeSinceFire.setToZero();         
            ISprite bullet = myProjectile.create();
            bullet.setLocation(new Coordinate(mySprite.getLocation().getX(),
                                              mySprite.getLocation().getY()));
            /**
             * this angle should have been intaken from the authoring and should still be in
             * degrees
             */
            bullet.getMovementStrategy().setOrientation(myAngle);

            myGame.bufferedAdd(bullet);
        }
    }

 

}
