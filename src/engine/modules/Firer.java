package engine.modules;

import java.util.ArrayList;
import java.util.List;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.Positionable;
import engine.effects.DefaultAffectable;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * This class creates a firer that handles and manages actions involving a creating an object to
 * fire
 * 
 */
public abstract class Firer extends DefaultAffectable implements IFireModule {

    private IAttribute myAmmo;
    private boolean ranged;
    private IAttribute myRange;
    private Positionable myParent;
    private List<ISprite> myFiredSprites;
    
    
    public Firer(Positionable parent){
    	myParent = parent;
    	myAmmo = new Attribute(AttributeType.AMMO);
    	myRange = new Attribute(AttributeType.FIRE_RANGE);
    	ranged = false;
    	myFiredSprites = new ArrayList<ISprite>();
    }

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
        attributes.add(myRange);
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
    
    public boolean getRanged(){
    	return ranged;
    }
    
    public void setRanged(boolean isRanged){
    	ranged = isRanged;
    }
    
    public IAttribute getRange(){
    	return myRange;
    }
    
    public void setRange(double range){
    	myRange.setValue(range);
    }
  
    protected List<ISprite> getFiredSprites(){
    	return myFiredSprites;
    }
     

}
