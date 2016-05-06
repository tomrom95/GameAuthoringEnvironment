package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.SpriteDefinition;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import util.Key;
import util.TimeDuration;


/**
 * This class provides the behavior necessary to implement a user controlled fire module in the
 * game.
 *
 * @author Dhrumil Timko
 */
public class UserFirer extends DirectionalFirer {

    private static final double FULL_CIRCLE = 360;
    private Key myFireKey;
    private Key myIncreaseKey;
    private Key myDecreaseKey;
    private double myAngleChange;

    public UserFirer (IGame game,
                      Positionable parent,
                      SpriteDefinition fireSprite,
                      Key fireKey,
                      Key increaseKey,
                      Key decreaseKey,
                      double waitTime,
                      double angle,
                      double change

    ) {
        super(game, fireSprite, parent, waitTime, angle);
        myFireKey = fireKey;
        myIncreaseKey = increaseKey;
        myDecreaseKey = decreaseKey;
        myAngleChange = change;
    }

    @Override
    public void update (TimeDuration duration) {
        super.firerUpdate(duration);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        if (keyEvent.getType() == InputType.KEY_PRESSED) {
            registerKeyPress(keyEvent.getKey());
        }
    }

    private void registerKeyPress (Key k) {
        if (k.isEqual(myFireKey)) {
            fireSprite();
        }
        else if (k.isEqual(myIncreaseKey)) {
            increaseAngle();
        }
        else if (k.isEqual(myDecreaseKey)) {
            decreaseAngle();
        }
        else {
            // do nothing
        }
    }

    private void increaseAngle () {
        setAngle(getAngle() + myAngleChange);
        if (getAngle() >= FULL_CIRCLE) {
            setAngle(getAngle() - FULL_CIRCLE);
        }
    }

    private void decreaseAngle () {
        setAngle(getAngle() - myAngleChange);
        if (getAngle() < 0) {
            setAngle(FULL_CIRCLE - getAngle());
        }
    }

    private void fireSprite () {
        super.fire();
    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        return new ArrayList<>();
    }

}
