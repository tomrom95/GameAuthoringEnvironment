package engine.modules;

import java.util.List;
import engine.IAttribute;
import engine.effects.DefaultAffectable;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import util.TimeDuration;


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
    public List<IAttribute> getAttributes () {
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
