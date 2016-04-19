package engine.modules;

import java.util.List;
import engine.IAttribute;
import engine.Positionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class creates a module that moves in based on velocity in a linear direction
 * TODO this class has lots of unimplemented parts, and shouldn't be returning nulls
 */
public class ConstantMover extends Mover {

    private IAttribute mySpeed;
    private Positionable mySprite;

    public ConstantMover (double xVel, double yVel, Positionable parent) {
        super(parent);
        getXVel().setValue(xVel);
        getYVel().setValue(yVel);
        mySprite = parent;

    }

    @Override
    public void setPath (List<Coordinate> newPath) {
    }

    @Override
    public List<Coordinate> getPath () {
        return null;
    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        return null;
    }

    @Override
    public void update (TimeDuration duration) {
        super.move(duration);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        return null;
    }

    public double getAngle () {
        return Math.atan(getYVel().getValueProperty().get() / getXVel().getValueProperty().get()) *
               180 / Math.PI;
    }

}
