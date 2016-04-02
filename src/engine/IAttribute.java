package engine;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


/**
 * This interface represents an attribute, or a pairing of a type and a value.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IAttribute extends Affectable {
    /**
     * @return the type of this attribute
     */
    AttributeType getType ();

    /**
     * @return the property that represents the value of this attribute
     */
    DoubleProperty getValueProperty ();

    /**
     * change the value of a this Attribute
     *
     * @param valueToSet value to change to
     */
    void setValue (double valueToSet);

    /**
     * This will expose the property list of the current effects
     * waiting to be applied to the attribute
     * 
     * @return
     */
    ObservableList<ObjectProperty<IEffect>> getEffects ();

    ObservableList<ObjectProperty<IInteractionEvent>> getEvents ();

}
