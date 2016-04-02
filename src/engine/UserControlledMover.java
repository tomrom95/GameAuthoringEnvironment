package engine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import util.Coordinate;
import util.Key;
import util.TimeDuration;
import engine.ISprite;
import interactionevents.IInteractionEvent;
import interactionevents.InputType;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;

public class UserControlledMover implements IMovementModule {

    ObjectProperty<IAttribute> myXVel;
    ObjectProperty<IAttribute> myYVel;
    ObjectProperty<Coordinate> myLocation;
    ObjectProperty<IAttribute> mySpeed;
    
    public UserControlledMover (double speed) {
       myXVel = new SimpleObjectProperty<>();
       myYVel = new SimpleObjectProperty<>();
       myLocation = new SimpleObjectProperty<>();
       mySpeed = new SimpleObjectProperty<>(new Attribute(speed));
    }
    @Override
    public void update (TimeDuration duration) {
        double xChange = distance(myXVel.get().getValueProperty().get(), duration.getMillis());
        double yChange = distance(myYVel.get().getValueProperty().get(), duration.getMillis());
        myLocation.set(getNextCoordinate(xChange, yChange));
        
    }

    private Coordinate getNextCoordinate (double xChange, double yChange) {
        return new Coordinate(myLocation.get().getX() + xChange, myLocation.get().getY() + yChange);
    }
    private double distance (double rate, double time) {
       return rate * time;
    }
    
    @Override
    public void applyEffect (IEffect effect) {
       myXVel.get().applyEffect(effect);
       myYVel.get().applyEffect(effect);    
    }

    
    @Override
    public void registerKeyEvent (KeyIOEvent event) {
        if(event.getType() == InputType.KEY_PRESSED) {
            registerKeyPress(event.getKey());
        } 
        if(event.getType() == InputType.KEY_RELEASED) {
            registerKeyRelease(event.getKey());
        }
    }
    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        //do nothing (could possibly track to here)
    }
    
    private void registerKeyPress (Key key) {
        // TODO Auto-generated method stub
        
    }
    
    private void registerKeyRelease (Key key) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public Coordinate getPosition () {
        return myLocation.get();
    }
    
    public static void main(String [ ] args) { 
        ISprite sprite = new Sprite ();
        UserControlledMover mover = new UserControlledMover(10);
        
        sprite.getMovementStrategyProperty().set(mover);
    }
    
    

}
