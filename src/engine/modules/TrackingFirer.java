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
            if (getTargets().size() == 0) {
                bullet.getMovementStrategy().setOrientationFromTracker(mySprite.getOrientation());
            }
            else {
                bullet.getMovementStrategy().setOrientationFromTracker(getTracker()
                        .calculateOrientationToClosestEnemy(mySprite.getLocation(), getTargets()));

            }

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

}
