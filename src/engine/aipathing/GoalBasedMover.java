package engine.aipathing;

import java.util.List;
import engine.IAttribute;
import engine.Positionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.modules.Mover;
import util.TimeDuration;

/**
 * Goal based pathing 
 * @author jonathanim
 *
 */
public class GoalBasedMover extends Mover {

    public GoalBasedMover (Positionable positionable) {
        super(positionable);
        // TODO Auto-generated constructor stub 
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

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

}
