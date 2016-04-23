package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
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

    private IAttribute myWaitTime;
    private Positionable mySprite;
    private SpriteDefinition myProjectile;
    private double myAngle;
    private TimeDuration myTimeSinceFire;

    public DirectionalFirer (
                             SpriteDefinition projectile,
                             Positionable sprite,
                             double waitTime,
                             double theta) {
        super(sprite);
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
        myTimeSinceFire = new TimeDuration(0);
        mySprite = sprite;
        myProjectile = projectile;
        myAngle = theta;
        myTimeSinceFire.setToZero();

    }

    @Override
    public void update (TimeDuration duration) {
        // updateTimeMap(duration);
        // removeSpritesBeyondRange();
        super.update(duration);
        fire(duration);
    }

    private void fire (TimeDuration duration) {

        myTimeSinceFire.increase(duration);

        if (myTimeSinceFire.getSeconds() >= myWaitTime.getValueProperty().get()) {
            fire();
            
            // addToTimeMap(bullet);

        }
    }
    
    protected void fire(){
        myTimeSinceFire.setToZero();
        ISprite bullet = myProjectile.create();
        bullet.setLocation(new Coordinate(mySprite.getLocation().getX(),
                                          mySprite.getLocation().getY()));
        /**
         * this angle should have been intaken from the authoring and should still be in
         * degrees
         */
        bullet.getMovementStrategy().setOrientation(myAngle);

        getGame().bufferedAdd(bullet);
        getFiredSprites().add(bullet);
    }

    protected void firerUpdate(TimeDuration t){
        super.update(t);
    }
    
    @Override
    protected List<IAttribute> getSpecificAttributes () {
        List<IAttribute> toAdd = new ArrayList<>();
        toAdd.add(myWaitTime);
        return toAdd;
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
