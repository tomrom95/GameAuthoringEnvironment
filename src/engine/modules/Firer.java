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
    public ObservableList<IAttribute> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

    protected ObjectProperty<IAttribute> getAmmo () {
        return myAmmo;
    }

}
