package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAttribute;
import engine.IPositionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
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

}
