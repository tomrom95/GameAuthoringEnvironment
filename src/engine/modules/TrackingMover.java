package engine.modules;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.IPositionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class builds a module that follows the closest enemy
 * 
 * @author Dhrumil Timko
 *
 */
public class TrackingMover extends Mover {

    private IAttribute mySpeed;
    private List<SpriteType> myEnemyList;
    private IPositionable mySprite;
    private EnemyTracker myTracker;
    private IGame myGame;

    public TrackingMover (double speed,
                          IGame game,
                          List<SpriteType> attackGroup,
                          IPositionable sprite) {
        super(sprite);
        myGame = game;
        mySpeed = new Attribute(speed, AttributeType.SPEED);
        myEnemyList = attackGroup;
        myTracker = new EnemyTracker();

    }

    @Override
    public void update (TimeDuration duration) {
        double newXVel =
                myTracker.calculateXVelToClosestEnemy(mySprite.getLocation(), myPotentialTargets(),
                                                      mySpeed.getValueProperty().get());
        setXVel(newXVel);

        double newYVel =
                myTracker.calculateYVelToClosestEnemy(mySprite.getLocation(), myPotentialTargets(),
                                                      mySpeed.getValueProperty().get());
        setYVel(newYVel);
        move(duration);

    }

    private List<ISprite> myPotentialTargets () {
        return myGame.getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> myEnemyList.contains(sprite))
                .collect(Collectors.toList());
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        // TODO Auto-generated method stub
        return null;
    }
}
