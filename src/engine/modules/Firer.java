package engine.modules;

import java.util.List;

import engine.IAttribute;
import engine.effects.DefaultAffectable;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * This class creates a firer that handles and manages actions involving a creating an object to
 * fire
 * TODO Is this class necessary?
 */
public abstract class Firer extends DefaultAffectable implements IFireModule {

    private IAttribute myAmmo;
    

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
        ObservableList<IAttribute> attributes = FXCollections.observableArrayList();
        attributes.add(myAmmo);
        attributes.addAll(getSpecificAttributes());
        return attributes;
    }

    protected abstract List<IAttribute> getSpecificAttributes();
   
    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

    public void setAmmo(double ammo){
    	myAmmo.setValue(ammo);
    }
    
    public IAttribute getAmmo (){
    	return myAmmo;
    }

}
