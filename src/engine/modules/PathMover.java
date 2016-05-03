package engine.modules;

import java.util.ArrayList;
import java.util.List;
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
 * @author Ryan josephtimko1
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
            move(getPath().get(myNextDestination));
            incrementIndex();
        }
        else {

            adjustVectors(getPath().get(myNextDestination));

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
        setOrientationFromTracker(pathTracker
                .calculateAbsoluteOrientationToEnemy(getParent().getLocation(), c));

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

    public int getPoint () {
        return myNextDestination;
    }

    public void setPoint (int x) {
        myNextDestination = x;
    }

    @Override
    public int getNextIndex () {
        return myNextDestination;
    }

    @Override
    public void setNextIndex (int index) {
        myNextDestination = index;
    }

}
