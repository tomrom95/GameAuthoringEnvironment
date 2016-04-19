package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import util.TimeDuration;


/**
 * This class builds a module that follows the closest enemy
 *
 * @author Dhrumil Timko
 *         TODO should this class extend DefaultAffectable?
 */
public class TrackingMover extends Mover {

    private IAttribute mySpeed;
    private List<SpriteType> myEnemyList;
    private Positionable mySprite;
    private EnemyTracker myTracker;
    private IGame myGame;

    public TrackingMover (double speed,
                          IGame game,
                          List<SpriteType> attackGroup,
                          Positionable sprite) {
        super(sprite);
        myGame = game;
        mySpeed = new Attribute(speed, AttributeType.SPEED);
        myEnemyList = attackGroup;
        mySprite = sprite;
        myTracker = new EnemyTracker();
    }

    @Override
    public void update (TimeDuration duration) {
        setOrientation(myTracker.calculateOrientationToClosestEnemy(mySprite.getLocation(), myPotentialTargets()));
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
        List<IAttribute> myList = new ArrayList<IAttribute>();
        myList.add(mySpeed);
        return myList;
    }
}
