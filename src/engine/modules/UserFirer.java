package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Key;
import util.TimeDuration;


/**
 * This class provides the behavior necessary to implement a user controlled fire module in the
 * game.
 *
 * @author Dhrumil Timko
 */
public class UserFirer extends DirectionalFirer {

    private Key myFireKey;
    private Key myIncreaseKey;
    private Key myDecreaseKey;
    private double myAngleChange;

    public UserFirer (Positionable parent,
                      SpriteDefinition fireSprite,
                      Key fireKey,
                      Key increaseKey,
                      Key decreaseKey,
                      double waitTime,
                      double angle,
                      double change

    ) {
        super(fireSprite, parent, waitTime, angle);
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
        if (keyEvent.getType() == InputType.KEY_PRESSED &&
            keyEvent.getKey().isEqual(myFireKey) | keyEvent.getKey()
                    .equals(myDecreaseKey) | keyEvent.getKey().equals(myIncreaseKey)) {
            registerKeyPress(keyEvent.getKey());
        }
    }

    private void registerKeyPress (Key k) {
        if(k.equals(myFireKey)){
            fireSprite();
        } else if (k.equals(myIncreaseKey)){
            increaseAngle();
        } else if(k.equals(myDecreaseKey)){
            decreaseAngle();
        } else{
            //do nothing
        }
    }

    private void increaseAngle () {
        setAngle(getAngle() + myAngleChange);
    }

    private void decreaseAngle () {
        setAngle(getAngle() - myAngleChange);
    }

    private void fireSprite () {
        super.fire();
    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        return new ArrayList<>();
    }

}
