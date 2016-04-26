package engine.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.Positionable;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.ControlKeys;
import util.Direction;
import util.Key;
import util.TimeDuration;


/**
 * This class serves as an implementation of Mover that serves as a module for a sprite that moves
 * based on specified IOEvents.
 *
 */

public class UserMover extends Mover {

    private Map<Direction, Key> myKeys;
    private Map<Direction, Boolean> myTraveling;

    public UserMover (double speed,
                      ControlKeys controls,
                      Positionable sprite) {
        super(sprite);
        setSpeedUnOriented(speed);
        makeKeyMap(controls);
        makeTravelingMap();

    }

    /**
     * Creates a map to store movement directions and a boolean to specify whether a particular
     * direction is a valid direction to move
     */
    private void makeTravelingMap () {
        myTraveling = new HashMap<>();
        myTraveling.put(Direction.UP, false);
        myTraveling.put(Direction.LEFT, false);
        myTraveling.put(Direction.RIGHT, false);
        myTraveling.put(Direction.DOWN, false);
    }

    /**
     * Creates a map to reference the Keys that map to the cardinal directions
     *
     * @param controls ControlKeys that specify what Key represents a direction
     */
    private void makeKeyMap (ControlKeys controls) {
        myKeys = new HashMap<>();
        myKeys.put(Direction.UP, controls.getUpKey());
        myKeys.put(Direction.LEFT, controls.getLeftKey());
        myKeys.put(Direction.RIGHT, controls.getRightKey());
        myKeys.put(Direction.DOWN, controls.getDownKey());
    }
    
    @Override
    protected void move (TimeDuration duration) {
        double xChange = distance(getXVel().getValueProperty().get(), durationToDouble(duration));
        double yChange = distance(getYVel().getValueProperty().get(), durationToDouble(duration));
        move(getNextCoordinate(xChange, yChange));
    }

    @Override
    public void update (TimeDuration duration) {
        move(duration);

    }

    @Override
    public void applyEffect (IEffect effect) {
        getXVel().applyEffect(effect);
        getYVel().applyEffect(effect);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent event) {
        if (event.getType() == InputType.KEY_PRESSED) {
            registerKeyPress(event.getKey());
        }
        else if (event.getType() == InputType.KEY_RELEASED) {
            registerKeyRelease(event.getKey());
        }
    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        // do nothing
    }

    private void registerKeyPress (Key key) {
        System.out.println(key.getKeyCode());
        myKeys.get(Direction.RIGHT).getKeyCode();
        if (key.isEqual(myKeys.get(Direction.RIGHT))) {
            goRight();
        }
        else if (key.isEqual(myKeys.get(Direction.LEFT))) {
            goLeft();
        }
        else if (key.isEqual(myKeys.get(Direction.UP))) {
            goUp();
        }
        else if (key.isEqual(myKeys.get(Direction.DOWN))) {
            goDown();
        }
    }

    /**
     * Moves the sprite based on the given Key. Handles the case if a sprite moves into an edge of
     * the game screen.
     *
     * @param key Key that is specified by the user
     */
    private void registerKeyRelease (Key key) {
        // TODO store these different responses as Runnable in a map
        if (key.isEqual(myKeys.get(Direction.RIGHT))) {
            if (myTraveling.get(Direction.LEFT)) {
                goLeft();
            }
            else {
                stopHorizontal();
            }
            myTraveling.put(Direction.RIGHT, false);
        }
        else if (key.isEqual(myKeys.get(Direction.LEFT))) {
            if (myTraveling.get(Direction.RIGHT)) {
                goRight();
            }
            else {
                stopHorizontal();
            }
            myTraveling.put(Direction.LEFT, false);
        }
        else if (key.isEqual(myKeys.get(Direction.UP))) {
            if (myTraveling.get(Direction.DOWN)) {
                goDown();
            }
            else {
                stopVertical();
            }
            myTraveling.put(Direction.UP, false);
        }
        else if (key.isEqual(myKeys.get(Direction.DOWN))) {
            if (myTraveling.get(Direction.UP)) {
                goUp();
            }
            else {
                stopVertical();
            }
            myTraveling.put(Direction.DOWN, false);
        }
    }

    private void stopVertical () {
        getYVel().setValue(NO_MOTION);
        myTraveling.put(Direction.DOWN, false);
        myTraveling.put(Direction.UP, false);
    }

    private void goRight () {
        getXVel().setValue(getSpeed());
        myTraveling.put(Direction.RIGHT, true);
        System.out.println(getXVel().getValueProperty().get());
    }

    private void goLeft () {
        getXVel().setValue(-getSpeed());
        myTraveling.put(Direction.LEFT, true);
    }

    private void goUp () {
        getYVel().setValue(-getSpeed());
        myTraveling.put(Direction.UP, true);
    }

    private void goDown () {
        getYVel().setValue(getSpeed());
        myTraveling.put(Direction.DOWN, true);
    }

    private void stopHorizontal () {
        getXVel().setValue(NO_MOTION);
        myTraveling.put(Direction.RIGHT, false);
        myTraveling.put(Direction.LEFT, false);
    }

    @Override
    public List<IAttribute> getSpecificAttributes () {
        return new ArrayList<>();
    }

}
