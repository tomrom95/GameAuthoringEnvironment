package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.SpriteGroup;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import util.TimeDuration;


/**
 * This class builds a module that follows the closest enemy
 *
 * @author Dhrumil Timko

 */
public class TrackingMover extends Mover {

    private IAttribute mySpeed;
    private List<SpriteType> myEnemyList;
    private Positionable mySprite;
    private EnemyTracker myTracker;
    private IGame myGame;

    public TrackingMover (double speed,
                          IGame game,
                          SpriteGroup spriteGroup,
                          Positionable sprite) {
        super(sprite);
        myGame = game;
        mySpeed = new Attribute(speed, AttributeType.SPEED);
        myEnemyList = spriteGroup.getSpriteTypes();
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

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        List<IAttribute> myList = new ArrayList<IAttribute>();
        myList.add(mySpeed);
        return myList;

    }
}
