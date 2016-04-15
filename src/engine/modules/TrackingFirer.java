package engine.modules;

import java.util.List;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.IPositionable;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.Key;
import util.TimeDuration;


/**
 * Locates the nearest enemy and fires an object
 * TODO should this class extend DefaultAffectable?
 */
public class TrackingFirer extends Firer {

    private List<SpriteType> myTargets;
    private SpriteDefinition myProjectile;
    private IAttribute myWaitTime;
    private EnemyTracker myTracker;
    private IAttribute myAmmo;
    private IGame myGame;
    private IPositionable mySprite;
    // TODO convert this to use TimeDuration
    private double myTimeSinceFire;

    public TrackingFirer (List<SpriteType> targets,
                          IGame game,
                          double waitTime,
                          SpriteDefinition projectile,
                          IPositionable sprite) {

        myTargets = targets;
        myGame = game;
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
        myTracker = new EnemyTracker();
        mySprite = sprite;
        myProjectile = projectile;
        myTimeSinceFire = 0;

    }

    @Override
    public void update (TimeDuration duration) {
        fire(duration);
    }

    private void fire (TimeDuration duration) {
        if (getTargets().isEmpty()) {
            return;
        }
        myTimeSinceFire += duration.getMillis();
        if (myTimeSinceFire >= myWaitTime.getValueProperty().get()) {
            ISprite bullet = myProjectile.create();
            bullet.setLocation(new Coordinate(mySprite.getLocation().getX(),
                                              mySprite.getLocation().getY()));
            double initialXVel =
                    myTracker.calculateXVelToClosestEnemy(bullet.getLocation(), getTargets(),
                                                          myProjectile.getMovementDefinition()
                                                                  .getSpeed());
            bullet.getMovementStrategy().setXVel(initialXVel);
            double initialYVel =
                    myTracker.calculateYVelToClosestEnemy(bullet.getLocation(), getTargets(),
                                                          myProjectile.getMovementDefinition()
                                                                  .getSpeed());
            bullet.getMovementStrategy().setYVel(initialYVel);
            myGame.bufferedAdd(bullet);
            myTimeSinceFire = 0;
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
        getAmmo().get().applyEffect(effect);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
    }

    private void registerKeyPress (Key fire) {
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        ObservableList<IAttribute> attributeList = FXCollections.observableArrayList();
        attributeList.add(myAmmo);
        attributeList.add(myWaitTime);
        return attributeList;
    }
}
