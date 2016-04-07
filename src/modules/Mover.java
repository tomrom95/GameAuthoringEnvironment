package modules;

import effects.IEffect;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.ISprite;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This abstract class provides the framework required to move sprites. Movement is handled as a
 * function of rate and time or as a coordinate specifying the next position.
 * 
 */

public abstract class Mover implements IMovementModule {

    public static final double NO_MOTION = 0;
    private ObjectProperty<IAttribute> myXVel;
    private ObjectProperty<IAttribute> myYVel;
    private ISprite myParent;

    public Mover (ISprite sprite) {
        myXVel = new SimpleObjectProperty<>(new Attribute(AttributeType.X_VEL));
        myYVel = new SimpleObjectProperty<>(new Attribute(AttributeType.Y_VEL));
        myParent = sprite;
    }

    private ObjectProperty<Coordinate> getLocation () {
        return myParent.getLocation();
    }

    protected void move (TimeDuration duration) {
        double xChange = distance(getXVel().get().getValueProperty().get(), duration.getMillis());
        double yChange = distance(getYVel().get().getValueProperty().get(), duration.getMillis());
        getLocation().set(getNextCoordinate(xChange, yChange));
    }

    protected void move (Coordinate coordinate) {
        getLocation().set(coordinate);
    }

    private Coordinate getNextCoordinate (double xChange, double yChange) {
        return new Coordinate(getLocation().get().getX() + xChange,
                              getLocation().get().getY() + yChange);
    }

    private double distance (double rate, double time) {
        return rate * time;
    }

    protected double getXDiff (double input) {
        return input - getLocation().get().getX();
    }

    protected double getYDiff (double input) {
        return input - getLocation().get().getY();
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

    abstract protected ObservableList<ObjectProperty<IAttribute>> getSpecificAttributes ();

    protected ObjectProperty<IAttribute> getXVel () {
        return myXVel;
    }

    protected ObjectProperty<IAttribute> getYVel () {
        return myYVel;
    }

}
