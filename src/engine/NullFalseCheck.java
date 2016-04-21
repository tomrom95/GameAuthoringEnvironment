package engine;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import util.TimeDuration;

public class NullFalseCheck implements ICheck {

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

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
