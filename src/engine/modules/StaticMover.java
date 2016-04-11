package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAttribute;
import engine.IPositionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;

public class StaticMover extends Mover {

    public StaticMover (IPositionable positionable) {
        super(positionable);
    }

    @Override
    public void update (TimeDuration duration) {
        //Do nothing
        
        
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        //Do nothing
        
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
