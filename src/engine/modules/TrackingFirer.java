// This entire file is part of my masterpiece.
// Joseph Timko

/*
 * This class is in the my masterpiece to show the extensibility of the firing module inheritance hierarchy.  Additionally this class implements
 * what I thought was the most visually impressive firing feature, tracking firing.  This firing module uses the EnemyTracker to orient the projectile
 * to the closest sprites that is a member of the defined SpriteGroup of enemies that the firer aims at.  This sprite can spawn any type of user defined
 * projectile, which is just any type of sprite.  This class passes a list of Sprites to the enemy tracker for sort through by filtering all the sprites
 * in the Game object using lambda expressions.  
 * 
 *  One way I designed these classes for testing, was by having the fire method sprites to the game.  Using JUnit to test all my firing modules, I simply set
 *  the game to be an empty game and using assertEquals statements I could test the firing modules to see that they were adding the proper number
 *  of projectiles to the game and inspect whether the orientation at which the projectile sprites were added was correct.  
 *  
 *  P.S. I did not include the enemy tracker in my masterpiece because it is a class that mostly just does arithmetic.  I do not mean for it to seem
 *  like it is a black box, but all it does is sort the list of sprites passed by a comparator using a lambda expression to find the closest sprite, and then 
 *  calculate the orientation from the parent sprite to the target sprite.
 * 
 */
package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.SpriteDefinition;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import util.Coordinate;
import util.TimeDuration;


/**
 * Locates the nearest enemy and fires a missile sprite defined in the authoring environment at
 * that enemy by setting its initial X and Y velocity based upon the location of the nearest enemy
 *  and adding it to the game with that velocity.
 *
 * @author josephtimko1
 *
 */
public class TrackingFirer extends Firer {

    private List<SpriteType> myTargets;
    private SpriteDefinition myProjectile;
    private Positionable mySprite;
    private TimeDuration myTimeSinceFire;

    public TrackingFirer (IGame game,
                          List<SpriteType> targets,
                          double waitTime,
                          SpriteDefinition projectile,
                          Positionable sprite) {
        super(game, sprite);
        myTargets = targets;
        setMyWaitTime(waitTime);
        mySprite = sprite;
        myProjectile = projectile;
        myTimeSinceFire = new TimeDuration(0);
        myTimeSinceFire.setToZero();

    }

    @Override
    public void update (TimeDuration duration) {
        super.update(duration);
        fire(duration);
    }

    private void fire (TimeDuration duration) {
        if (getTargets().isEmpty()) {
            return;
        }
        myTimeSinceFire.increase(duration);
        if (myTimeSinceFire.getSeconds() >= getMyWaitTime().getValueProperty().get()) {
            ISprite bullet = myProjectile.create();
            bullet.setLocation(new Coordinate(mySprite.getLocation().getX(),
                                              mySprite.getLocation().getY()));

            bullet.getMovementStrategy().setOrientationFromTracker(mySprite.getOrientation());
            setProjectileToTrackingOrientation(bullet);

            getGame().bufferedAdd(bullet);
            getFiredSprites().add(bullet);
            myTimeSinceFire.setToZero();

        }
    }

    private List<ISprite> getTargets () {

        return getGame().getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> myTargets.contains(sprite.getType()))
                .collect(Collectors.toList());
    }

    private void setProjectileToTrackingOrientation (ISprite projectile) {
        if (getTargets().size() > 0) {
            projectile.getMovementStrategy().setOrientationFromTracker(getTracker()
                    .calculateOrientationToClosestEnemy(mySprite.getLocation(), getTargets()));
        }
    }

    /**
     *There are no attributes specific to this module as of yet that need to be observed,
     *return empty list.
     */
    @Override
    protected List<IAttribute> getSpecificAttributes () {
        List<IAttribute> toAdd = new ArrayList<>();
   
        return toAdd;
    }

}
