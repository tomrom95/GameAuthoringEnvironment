package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import util.Coordinate;
import util.TimeDuration;


/**
 * Locates the nearest enemy and fires a missile sprite defined in the authoring environment at
 * that enemy, setting its initial X and Y velocity based upon the near enemy
 * 
 * @author Timko
 * 
 */
public class TrackingFirer extends Firer {

    private List<SpriteType> myTargets;
    private SpriteDefinition myProjectile;
    private IAttribute myWaitTime;
    private Positionable mySprite;
    private TimeDuration myTimeSinceFire;

    public TrackingFirer (IGame game, List<SpriteType> targets,
                          double waitTime,
                          SpriteDefinition projectile,
                          Positionable sprite) {
    	super(game, sprite);
        myTargets = targets;
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
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
        if (myTimeSinceFire.getSeconds() >= myWaitTime.getValueProperty().get()) {
            ISprite bullet = myProjectile.create();
            bullet.setLocation(new Coordinate(mySprite.getLocation().getX(),
                                              mySprite.getLocation().getY()));
//does the actual game orientaion deal in radians?  I think yes
//            bullet.getMovementStrategy().setOrientation(getTracker().calculateOrientationToClosestEnemy(mySprite.getLocation(), getTargets()));
            bullet.getMovementStrategy().setOrientationFromTracker(getTracker().calculateOrientationToClosestEnemy(mySprite.getLocation(), getTargets()));

            System.out.println("BULLET ORIENTATION IS: "+bullet.getOrientation());
            getGame().bufferedAdd(bullet);
            getFiredSprites().add(bullet);
            myTimeSinceFire.setToZero();
            
        }
    }

    private List<ISprite> getTargets () {
        
//        List<ISprite> test = getGame().getLevelManager().getCurrentLevel().getSprites().stream()
//                .filter(sprite -> myTargets.contains(sprite.getType()))
//                .collect(Collectors.toList());
//        System.out.println("SIZE OF TARGET ARRAY IS: "+test.size());
//        return test;
        return getGame().getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> myTargets.contains(sprite.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public void applyEffect (IEffect effect) {
        // TODO move to firer? and should probably apply to waitTime also
        getAmmo().applyEffect(effect);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        List<IAttribute> toAdd = new ArrayList<>();
        toAdd.add(myWaitTime);
        return toAdd;
    }

}
