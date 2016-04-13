package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.IPositionable;
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

    private List<SpriteType> myTargets;
    private SpriteDefinition myProjectile;
    private IAttribute myWaitTime;
    private EnemyTracker myTracker;
    private IAttribute myAmmo;
    private TimeDuration lastFire;
    private IGame myGame;
    private IPositionable mySprite;
   

  
    public TrackingFirer (List<SpriteType> targets, IGame game, double waitTime, SpriteDefinition projectile, IPositionable sprite) {

        myTargets = targets;
        myGame = game;
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
        myTracker = new EnemyTracker();
        lastFire = new TimeDuration(0);
        mySprite = sprite;
        myProjectile = projectile;
        
        /*
         * 
         */
        
    }

    @Override
    public void update (TimeDuration duration) {
        fire(duration);
    }

    private void fire (TimeDuration duration) {
        /*
         * 
         */
        if((duration.getMillis() - lastFire.getMillis()) >= myWaitTime.getValueProperty().get()){
            ISprite bullet = myProjectile.create();
            bullet.setLocation(new Coordinate(mySprite.getLocation().getX(), mySprite.getLocation().getY()));
            
            //access to the velocity of the sprite
            double initialXVel = myTracker.calculateXVelToClosestEnemy(bullet.getLocation(), getTargets(), myProjectile.getMovementDefinition().getSpeed()); 
            bullet.getMovementStrategy().setXVel(initialXVel);
            double initialYVel = myTracker.calculateYVelToClosestEnemy(bullet.getLocation(), getTargets(), myProjectile.getMovementDefinition().getSpeed());
            bullet.getMovementStrategy().setYVel(initialYVel);            
            myGame.add(bullet);
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
        /*
         * 
         * what the fuck, why isn't this happening in fire
         * also what where is this effect
         * no notion of ammo in constructor or definition
         * 
         */
//        myWaitTime.applyEffect(effect);
        getAmmo().get().applyEffect(effect);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
       

    }

    @SuppressWarnings("unused")
    private void registerKeyPress (Key fire) {
      
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {

    }

    @Override
    public List<IAttribute> getAttributes () {
        List<IAttribute> attributeList = new ArrayList<>();
        attributeList.add(myAmmo);
        attributeList.add(myWaitTime);
        return attributeList;
    }
}
