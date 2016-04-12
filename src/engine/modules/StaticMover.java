package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAttribute;
import engine.IPositionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.Coordinate;
import util.TimeDuration;


public class StaticMover extends Mover {

    public StaticMover (IPositionable positionable) {
        super(positionable);
    }

    @Override
    public void update (TimeDuration duration) {
        // Do nothing

    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // Do nothing

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // Do nothing

    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        return new ArrayList<>();
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
