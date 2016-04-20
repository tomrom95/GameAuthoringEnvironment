package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private EnemyTracker myTracker;
    private IGame myGame;
    private Positionable mySprite;
    private TimeDuration myTimeSinceFire;

    public TrackingFirer (List<SpriteType> targets,
                          IGame game,
                          double waitTime,
                          SpriteDefinition projectile,
                          Positionable sprite) {
    	super(sprite);
        myTargets = targets;
        myGame = game;
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
        myTracker = new EnemyTracker();
        mySprite = sprite;
        myProjectile = projectile;
        myTimeSinceFire = new TimeDuration();
        myTimeSinceFire.setToZero();

    }

    @Override
    public void update (TimeDuration duration) {
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
            bullet.getMovementStrategy().setOrientation(myTracker.calculateOrientationToClosestEnemy(mySprite.getLocation(), getTargets()));
            myGame.bufferedAdd(bullet);
            myTimeSinceFire.setToZero();
            
        }
    }

    private List<ISprite> getTargets () {
        return myGame.getLevelManager().getCurrentLevel().getSprites().stream()
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
	protected List<IAttribute> getSpecificAttributes() {
		List<IAttribute> toAdd = new ArrayList<>();
		toAdd.add(myWaitTime);
		return toAdd;
	}

   
}
