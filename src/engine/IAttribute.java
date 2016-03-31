package engine;

import javafx.beans.property.DoubleProperty;


public interface IAttribute extends Affectable {
    AttributeType getType ();

    DoubleProperty getValueProperty ();

    void setValue (double valueToSet);

}
