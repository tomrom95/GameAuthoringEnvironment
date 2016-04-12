package engine.modules;

import java.util.List;
import engine.IAttribute;
import engine.IPositionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class creates a module that moves in based on velocity in a linear direction
 *
 */
public class ConstantMover extends Mover {

    public ConstantMover (double xVel, double yVel, IPositionable parent) {
        super(parent);
        getXVel().setValue(xVel);
        getYVel().setValue(yVel);
    }

    @Override
    public void update (TimeDuration duration) {
        super.move(duration);

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
        return null;
    }

    @Override
    public void setPath (List<Coordinate> newPath) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Coordinate> getPath () {
        // TODO Auto-generated method stub
        return null;
    }

}
