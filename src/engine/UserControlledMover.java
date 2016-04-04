package engine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.Key;
import util.TimeDuration;
import java.util.HashMap;
import java.util.Map;
import interactionevents.InputType;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import util.Direction;


public class UserControlledMover implements IMovementModule {

    private static final double NO_MOTION = 0;
    private ObjectProperty<IAttribute> myXVel;
    private ObjectProperty<IAttribute> myYVel;
    private ObjectProperty<Coordinate> myLocation;
    private ObjectProperty<IAttribute> mySpeed;

    private Map<Direction, Key> myKeys;
    private Map<Direction, Boolean> myTraveling;

    public UserControlledMover (Coordinate startingLocation, double speed, ControlKeys controls) {
        myXVel = new SimpleObjectProperty<>(new Attribute());
        myYVel = new SimpleObjectProperty<>(new Attribute());
        myLocation = new SimpleObjectProperty<>(startingLocation);
        mySpeed = new SimpleObjectProperty<>(new Attribute(speed));
        makeKeyMap(controls);
        makeTravelingMap();

    }

    private void makeTravelingMap () {
        myTraveling = new HashMap<>();
        myTraveling.put(Direction.UP, false);
        myTraveling.put(Direction.LEFT, false);
        myTraveling.put(Direction.RIGHT, false);
        myTraveling.put(Direction.DOWN, false);
    }

    private void makeKeyMap (ControlKeys controls) {
        myKeys = new HashMap<>();
        myKeys.put(Direction.UP, controls.getUpKey());
        myKeys.put(Direction.LEFT, controls.getLeftKey());
        myKeys.put(Direction.RIGHT, controls.getRightKey());
        myKeys.put(Direction.DOWN, controls.getDownKey());
    }

    @Override
    public void update (TimeDuration duration) {
        double xChange = distance(myXVel.get().getValueProperty().get(), duration.getMillis());
        double yChange = distance(myYVel.get().getValueProperty().get(), duration.getMillis());
        myLocation.set(getNextCoordinate(xChange, yChange));

    }

    private Coordinate getNextCoordinate (double xChange, double yChange) {
        return new Coordinate(myLocation.get().getX() + xChange, myLocation.get().getY() + yChange);
    }

    private double distance (double rate, double time) {
        return rate * time;
    }

    @Override
    public void applyEffect (IEffect effect) {
        myXVel.get().applyEffect(effect);
        myYVel.get().applyEffect(effect);
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

    private void registerKeyRelease (Key key) {
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
        myYVel.get().setValue(NO_MOTION);
        myTraveling.put(Direction.DOWN, false);
        myTraveling.put(Direction.UP, false);
    }

    private void goRight () {
        myXVel.get().setValue(mySpeed.get().getValueProperty().get());
        myTraveling.put(Direction.RIGHT, true);
    }

    private void goLeft () {
        myXVel.get().setValue(-mySpeed.get().getValueProperty().get());
        myTraveling.put(Direction.LEFT, true);
    }

    private void goUp () {
        myYVel.get().setValue(-mySpeed.get().getValueProperty().get());
        myTraveling.put(Direction.UP, true);
    }

    private void goDown () {
        myYVel.get().setValue(mySpeed.get().getValueProperty().get());
        myTraveling.put(Direction.DOWN, true);
    }

    private void stopHorizontal () {
        myXVel.get().setValue(NO_MOTION);
        myTraveling.put(Direction.RIGHT, false);
        myTraveling.put(Direction.LEFT, false);
    }

    @Override
    public ObjectProperty<Coordinate> getLocationProperty () {
        return myLocation;
    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        ObservableList<ObjectProperty<IAttribute>> attributeList =
                FXCollections.observableArrayList();
        attributeList.addAll(mySpeed, myXVel, myYVel);
        return attributeList;
    }

}
