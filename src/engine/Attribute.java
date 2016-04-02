package engine;

import interactionevents.IInteractionEvent;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Attribute implements IAttribute {

    DoubleProperty myValue;

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

}
