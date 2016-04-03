package engine;

import interactionevents.IInteractionEvent;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import util.TimeDuration;


public class Attribute implements IAttribute {

    private DoubleProperty myValue;

    public Attribute () {
        // TODO introduce idea of default
        myValue = new SimpleDoubleProperty(0);
    }

    public Attribute (double value) {
        myValue = new SimpleDoubleProperty(value);
    }

    @Override
    public void applyEffect (IEffect effect) {
        effect.applyToAttribute(this);

    }

    

    @Override
    public AttributeType getType () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DoubleProperty getValueProperty () {
        return myValue;
    }

    @Override
    public void setValue (double valueToSet) {
       myValue.set(valueToSet);

    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registerKeyEvent (KeyIOEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ObservableList<ObjectProperty<IEffect>> getEffects () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObservableList<ObjectProperty<IInteractionEvent>> getEvents () {
        // TODO Auto-generated method stub
        return null;
    }

}
