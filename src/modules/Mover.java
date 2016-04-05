package modules;

import effects.IEffect;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;

public abstract class Mover implements IMovementModule {

    public static final double NO_MOTION = 0;
    private ObjectProperty<IAttribute> myXVel;
    private ObjectProperty<IAttribute> myYVel;
    private ObjectProperty<Coordinate> myLocation;
    
    public Mover (ObjectProperty<Coordinate> location) {
        myXVel = new SimpleObjectProperty<>(new Attribute(AttributeType.X_VEL));
        myYVel = new SimpleObjectProperty<>(new Attribute(AttributeType.Y_VEL));
        myLocation = location;
    }
    
    protected void move (TimeDuration duration) {
        double xChange = distance(getXVel().get().getValueProperty().get(), duration.getMillis());
        double yChange = distance(getYVel().get().getValueProperty().get(), duration.getMillis());
        myLocation.set(getNextCoordinate(xChange, yChange));
    }
    
    private Coordinate getNextCoordinate (double xChange, double yChange) {
        return new Coordinate(myLocation.get().getX() + xChange, myLocation.get().getY() + yChange);
    }

    private double distance (double rate, double time) {
        return rate * time;
    }
    
    protected double getXDiff(double input) { 
        return input - myLocation.get().getX();
    }
    
    protected double getYDiff(double input) { 
        return input - myLocation.get().getY();
    }
    
    abstract public void update (TimeDuration duration);

    @Override
    public void applyEffect (IEffect effect) {
        getAttributes().forEach(a -> effect.applyToAttribute(a.get()));
        
    }

    @Override
    abstract public void registerKeyEvent (KeyIOEvent keyEvent);

    @Override
    abstract public void registerMouseEvent (MouseIOEvent mouseEvent);

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        ObservableList<ObjectProperty<IAttribute>> attributes = FXCollections.observableArrayList();
        attributes.addAll(myXVel, myYVel);
        attributes.addAll(getSpecificAttributes());
        return attributes;
    }
    
    abstract protected ObservableList<ObjectProperty<IAttribute>> getSpecificAttributes();

    @Override
    public ObjectProperty<Coordinate> getLocationProperty () {
        return myLocation;
    }
    
    protected ObjectProperty<IAttribute> getXVel () {
        return myXVel;
    }
    
    protected ObjectProperty<IAttribute> getYVel () {
        return myYVel;
    }

}
