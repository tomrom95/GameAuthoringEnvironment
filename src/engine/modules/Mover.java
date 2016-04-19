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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This abstract class provides the framework required to move sprites. Movement is handled as a
 * function of rate and time or as a coordinate specifying the next position.
 *
 */
public abstract class Mover extends DefaultAffectable implements IMovementModule {

    public static final double NO_MOTION = 0;
    private IAttribute myXVel;
    private IAttribute myYVel;
    private Positionable myParent;
    private List<Coordinate> myPath;

    public Mover (Positionable positionable) {
        myXVel = new Attribute(AttributeType.X_VEL);
        myYVel = new Attribute(AttributeType.Y_VEL);
        myParent = positionable;
        myPath = new ArrayList<>();
    }

    private Coordinate getLocation () {
        return myParent.getLocation();
    }

    protected void move (TimeDuration duration) {
        double xChange = distance(getXVel().getValueProperty().get(), duration.getSeconds());
        double yChange = distance(getYVel().getValueProperty().get(), duration.getSeconds());
        move(getNextCoordinate(xChange, yChange));
    }

    protected void move (Coordinate coordinate) {
        myParent.setLocation(coordinate);
    }

    private Coordinate getNextCoordinate (double xChange, double yChange) {
        return new Coordinate(getLocation().getX() + xChange,
                              getLocation().getY() + yChange);
    }

    private double distance (double rate, double time) {
        return rate * time;
    }

    protected double getXDiff (double input) {
        return input - getLocation().getX();
    }

    protected double getYDiff (double input) {
        return input - getLocation().getY();
    }

    @Override
    public abstract void update (TimeDuration duration);

    @Override
    public void applyEffect (IEffect effect) {
        getAttributes().forEach(a -> effect.applyToAttribute(a));
    }

    @Override
    public abstract void registerKeyEvent (KeyIOEvent keyEvent);

    @Override
    public abstract void registerMouseEvent (MouseIOEvent mouseEvent);

    @Override
    public ObservableList<IAttribute> getAttributes () {
        ObservableList<IAttribute> attributes = FXCollections.observableArrayList();
        attributes.add(getXVel());
        attributes.add(getYVel());
        attributes.addAll(getSpecificAttributes());
        return attributes;
    }

    protected abstract List<IAttribute> getSpecificAttributes ();

    protected IAttribute getXVel () {
        return myXVel;
    }

    protected IAttribute getYVel () {
        return myYVel;
    }

    @Override
    public void setXVel (double newVel) {
        myXVel.setValue(newVel);
    }

    @Override
    public void setYVel (double newVel) {
        myYVel.setValue(newVel);
    }

    @Override
    public void setPath (List<Coordinate> path) {
        myPath = path;
    }

    @Override
    public List<Coordinate> getPath () {
        return myPath;
    }
}
