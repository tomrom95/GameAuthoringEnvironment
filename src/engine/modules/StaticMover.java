package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAttribute;
import engine.Positionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.TimeDuration;


/**
 * This class creates a module that is immovable. Used primarily for towers in TowerDefense games
 *
 */
public class StaticMover extends Mover {

    public StaticMover (Positionable positionable) {
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
    public int getNextIndex () {

        return 0;
    }

    @Override
    public void setNextIndex (int index) {

    }

}
