package engine;

import java.util.ArrayList;
import java.util.List;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * This class serves to hold values that the user labels as attributes. This class works with the
 * Sprite class
 * to give user created sprites the notion of attributes that are affected by conditions and events.
 *
 */

public class Attribute implements IAttribute {

    private DoubleProperty myValue;
    private AttributeType myType;
    private ObservableList<ObjectProperty<IEffect>> myEffects;
    private static final double DEFAULT_STARTING_VALUE = 0;

    public Attribute (AttributeType type) {
        this(DEFAULT_STARTING_VALUE, type);
    }

    public Attribute (double value, AttributeType type) {
        myValue = new SimpleDoubleProperty(value);
        myType = type;
        myEffects = FXCollections.observableArrayList();
    }

    @Override
    public void applyEffect (IEffect effect) {
        myEffects.add(new SimpleObjectProperty<>(effect));
    }

    @Override
    public AttributeType getType () {
        return myType;
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
    public void registerKeyEvent (KeyIOEvent event) {
        // do nothing
    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        // do nothing
    }

    @Override
    public List<IAttribute> getAttributes () {
        List<IAttribute> attributes = new ArrayList<>();
        attributes.add(this);
        return attributes;
    }

    @Override
    public void update (TimeDuration duration) {
        myEffects.forEach(e -> e.get().applyToAttribute(this));
        myEffects.forEach(e -> e.get().update(duration));
        removeCompletedEffects(duration);

        System.out.print(myType.getType() + " ");
        System.out.println(myValue.get());
    }

    /**
     * Removes time or condition dependent effects that are invalid or have
     * expired
     *
     * @param duration frame rate specified by the level
     */
    private void removeCompletedEffects (TimeDuration duration) {
        myEffects.removeIf(e -> e.get().hasCompleted());
    }

    @Override
    public ObservableList<ObjectProperty<IEffect>> getEffects () {
        return myEffects;
    }

}
