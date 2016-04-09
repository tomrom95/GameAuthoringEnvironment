package engine.modules;

import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IPositionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class provides an implementation of Mover that serves as a module that moves sprites based
 * on a specified coordinate path.
 * 
 *
 */

public class PathMover extends Mover {

    public static final int PIXEL_RANGE = 5;

    private ObjectProperty<IAttribute> mySpeed;
    private List<Coordinate> myPoints;
    private int myNextDestination;

    public PathMover (double speed,
                            List<Coordinate> points,
                            IPositionable positionable) {
        super(positionable);
        mySpeed = new SimpleObjectProperty<>(new Attribute(speed, AttributeType.SPEED));
        myPoints = points;
        myNextDestination = 0;
        

    }

    @Override
    public void update (TimeDuration duration) {
        if (myPoints.size() == 0) {
            return;
        }
        if (overshootNext(duration)) {
            move(myPoints.get(myNextDestination));
            incrementIndex();
        }
        else {
            adjustVectors();
            move(duration);
        }

    }

    /**
     * Prevents movement glitches on the GUI by moving the sprite to the next coordinate instead of
     * overshooting the location and portraying an invalid path
     * 
     * @param duration time frame of the game that is computed to provide the animation
     * @return boolean flagging whether the sprite will overshoot its coordinate target
     */
    private boolean overshootNext (TimeDuration duration) {
        double distancePossible = duration.getMillis() * mySpeed.get().getValueProperty().get();
        double distance = Math.sqrt(xDifference() * xDifference() + yDifference() * yDifference());
        return distancePossible > distance;
    }

    private double xDifference () {
        return getXDiff(myPoints.get(myNextDestination).getX());
    }

    private double yDifference () {
        return getYDiff(myPoints.get(myNextDestination).getY());
    }

    private void incrementIndex () {
        if (myNextDestination < myPoints.size() - 1) {
            myNextDestination++;
        }
    }

    /**
     * Computes and adjusts the xpos and ypos vectors each time fame to specify the next location of
     * the sprite
     */
    private void adjustVectors () {

        double xDiff = xDifference();
        double yDiff = yDifference();
        double constant =
                Math.sqrt(square(mySpeed.get().getValueProperty().get()) /
                          (square(xDiff) + square(yDiff)));
        if (!isZero(constant)) {
            setXVelocity(xDiff * constant);
            setYVelocity(yDiff * constant);
        }
        else {
            setXVelocity(NO_MOTION);
            setYVelocity(NO_MOTION);
        }

    }

    private boolean isZero (double val) {
        return Math.abs(val) < 2 * Double.MIN_VALUE;
    }

    private void setXVelocity (double vel) {
        getXVel().get().getValueProperty().set(vel);
    }

    private void setYVelocity (double vel) {
        getYVel().get().getValueProperty().set(vel);
    }

    private double square (double input) {
        return input * input;
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // do nothing
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // do nothing
    }

    @Override
    protected ObservableList<ObjectProperty<IAttribute>> getSpecificAttributes () {
        ObservableList<ObjectProperty<IAttribute>> attributes = FXCollections.observableArrayList();
        attributes.add(mySpeed);
        return attributes;
    }

}
