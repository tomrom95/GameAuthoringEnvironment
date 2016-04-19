package engine.modules;

import engine.IAttribute;
import engine.effects.DefaultAffectable;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * This class creates a firer that handles and manages actions involving a creating an object to
 * fire
 * TODO Is this class necessary?
 */
public class Firer extends DefaultAffectable implements IFireModule {

    private ObjectProperty<IAttribute> myAmmo;

    @Override
    public void applyEffect (IEffect effect) {

    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {

    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        return null;
    }

    @Override
    public void update (TimeDuration duration) {

    }

    protected ObjectProperty<IAttribute> getAmmo () {
        return myAmmo;
    }

}
