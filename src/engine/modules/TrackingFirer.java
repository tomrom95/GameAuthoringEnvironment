package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAdder;
import engine.IAttribute;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.Coordinate;
import util.Key;
import util.TimeDuration;


/**
 * Locates the nearest enemy and fires an object
 *
 */
public class TrackingFirer extends Firer {

    // private List<SpriteDefinition> myTargets;
    private List<ISprite> myTargets;
    private SpriteDefinition myProjectile;
    private IAdder myAdder;
    private double myWaitTime;
    private EnemyTracker myTracker;
    private boolean userControlled;
    private IAttribute myAmmo;
    private TimeDuration lastFire;
    private int timesFired;

    // public TrackingFirer (List<SpriteDefinition> targets, Key fire, double waitTime, boolean
    // userControls) {
    public TrackingFirer (List<ISprite> targets, double waitTime) {

        myTargets = targets;
        myWaitTime = waitTime;
        myTracker = new EnemyTracker();
        lastFire = new TimeDuration(0);
        timesFired = 0;
    }

    @Override
    public void update (TimeDuration duration) {
        fire(duration);
    }

    private void fire (TimeDuration duration) {
        if((duration.getMillis() - lastFire.getMillis()) >= myWaitTime){
            ISprite bullet = myProjectile.create();
            bullet.setLocation(new Coordinate(bullet.getLocation().getX(), bullet.getLocation()
                    .getY(), myTracker.getOrientationToClosestEnemy(myTargets, bullet.getLocation())));
            myAdder.add(bullet, bullet.getLocation());
            lastFire = new TimeDuration(duration.getMillis());
            timesFired += 1;
        } 
    }

    @Override
    public void applyEffect (IEffect effect) {
        getAmmo().get().applyEffect(effect);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        //
        // if (keyEvent.getType() == InputType.KEY_PRESSED &&
        // keyEvent.getKey().isEqual(myFireKey)) {
        // registerKeyPress(keyEvent.getKey());
        // }

    }

    private void registerKeyPress (Key fire) {
        //
        // ISprite bullet = myProjectile.create();
        // myAdder.add(bullet, bullet.getLocation());
        //
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // do nothing

    }

    @Override
    public List<IAttribute> getAttributes () {
        List<IAttribute> attributeList = new ArrayList<>();
        attributeList.add(myAmmo);
        return attributeList;
    }
}
