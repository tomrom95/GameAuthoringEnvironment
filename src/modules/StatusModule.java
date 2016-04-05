package modules;

import effects.IEffect;
import engine.IAttribute;
import engine.IStatusModule;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import util.TimeDuration;

public class StatusModule implements IStatusModule {

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void applyEffect (IEffect effect) {
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
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean shouldBeRemoved () {
        // TODO Auto-generated method stub
        return false;
    }

}
