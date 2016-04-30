package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.Positionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
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

    private int myNextDestination;
    private EnemyTracker pathTracker;
    public PathMover (double speed,
                      List<Coordinate> points,
                      Positionable positionable) {
        super(positionable);
        setSpeed(speed);
        setPath(points);
        pathTracker = new EnemyTracker();
        myNextDestination = 0;
    }

    @Override
    public void update (TimeDuration duration) {
        if (getPath().size() == 0) {
            return;
        }
        if (overshootNext(duration)) {
            System.out.println(getPath().size());
            move(getPath().get(myNextDestination));
            incrementIndex();
        }
        else {
            
            adjustVectors(getPath().get(myNextDestination));
            System.out.println("Next Destination Index = " +myNextDestination);
            //TODO : this should be a thing
//            incrementIndex();
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
        double distancePossible = duration.getMillis() * getSpeed();
        double distance = Math.sqrt(xDifference() * xDifference() + yDifference() * yDifference());
        return distancePossible > distance;
    }

    private double xDifference () {
        return getXDiff(getPath().get(myNextDestination).getX());
    }

    private double yDifference () {
        return getYDiff(getPath().get(myNextDestination).getY());
    }

    private void incrementIndex () {
        if (myNextDestination < getPath().size() - 1) {
            myNextDestination++;
        }
    }

    /**
     * Computes and adjusts the xpos and ypos vectors each time fame to specify the next location of
     * the sprite
     */
    private void adjustVectors (Coordinate c) {

        
        setOrientation(pathTracker.calculateAbsoluteOrientationToEnemy(getParent().getLocation(), c));
        
//        double xDiff = xDifference();
//        double yDiff = yDifference();
//        setOrientation(Math.tan(yDiff/xDiff));
//        double constant =
//                Math.sqrt(square(getSpeed()) /
//                          (square(xDiff) + square(yDiff)));
//        if (!isZero(constant)) {
//            setXVelocity(xDiff * constant);
//            setYVelocity(yDiff * constant);
//        }
//        else {
//            System.out.println("IT IS CALLING TO SET MOVER TO NO MOTION");
//            setXVelocity(NO_MOTION);
//            setYVelocity(NO_MOTION);
//        }

    }

    private boolean isZero (double val) {
        return Math.abs(val) < 2 * Double.MIN_VALUE;
    }

    private void setXVelocity (double vel) {
        getXVel().getValueProperty().set(vel);
    }

    private void setYVelocity (double vel) {
        getYVel().getValueProperty().set(vel);
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
    protected List<IAttribute> getSpecificAttributes () {
        return new ArrayList<>();
        
    }

}
