package engine;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class NullFalseCheck implements ICheck {

    public NullFalseCheck () {
        System.out.println("made");
    }
   
    @Override
    public void alterAttribute () {
        //do nothing

    }

    @Override
    public BooleanProperty getStatus () {
        return new SimpleBooleanProperty(false);
    }

}
