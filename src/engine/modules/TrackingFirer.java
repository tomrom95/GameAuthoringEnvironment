package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAdder;
import engine.IAttribute;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import util.Coordinate;
import util.Key;
import util.TimeDuration;


/**
 * Locates the nearest enemy and fires an object
 *
 */
public class TrackingFirer extends Firer {

    // private List<SpriteDefinition> myTargets;
    private List<SpriteType> myTargets;
    private SpriteDefinition myProjectile;
    private IAdder myAdder;
    private IAttribute myWaitTime;
    private EnemyTracker myTracker;
    //private boolean userControlled;
    private IAttribute myAmmo;
    private TimeDuration lastFire;
    private IGame myGame;
   

    // public TrackingFirer (List<SpriteDefinition> targets, Key fire, double waitTime, boolean
    // userControls) {
    public TrackingFirer (List<SpriteType> targets, IGame game, double waitTime) {

        myTargets = targets;
        myGame = game;
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
        myTracker = new EnemyTracker();
        lastFire = new TimeDuration(0);
        
    }

    @Override
    public void update (TimeDuration duration) {
        fire(duration);
    }

    private void fire (TimeDuration duration) {
        if((duration.getMillis() - lastFire.getMillis()) >= myWaitTime.getValueProperty().get()){
            ISprite bullet = myProjectile.create();
            //TODO tower location
            bullet.setLocation(new Coordinate(bullet.getLocation().getX(), bullet.getLocation()
                    .getY(), myTracker.getOrientationToClosestEnemy(getTargets(), bullet.getLocation())));
            myAdder.add(bullet, bullet.getLocation());
            lastFire = new TimeDuration(duration.getMillis());
            
        } 
    }

    private List<ISprite> getTargets () {
        return myGame.getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> myTargets.contains(sprite))
                .collect(Collectors.toList());
    }

    @Override
    public void applyEffect (IEffect effect) {
        myWaitTime.applyEffect(effect);
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
        attributeList.add(myWaitTime);
        return attributeList;
    }
}
