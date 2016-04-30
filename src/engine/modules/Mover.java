package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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

    private static final double MULTIPLIER = 0.01;
    public static final double NO_MOTION = 0;
    public static final double RADS_TO_DEGREES = 180 / Math.PI;
    public static final double DEGREES_TO_RADS = Math.PI / 180;

    private IAttribute myXVel;
    private IAttribute myYVel;
    private IAttribute myOrientation;
    private IAttribute mySpeed;
    private Positionable myParent;
    private List<Coordinate> myPath;

    public Mover (Positionable positionable) {
        myXVel = new Attribute(AttributeType.X_VEL);
        myYVel = new Attribute(AttributeType.Y_VEL);
        mySpeed = new Attribute(AttributeType.SPEED);
        myOrientation = new Attribute(AttributeType.ORIENTATION);
        myParent = positionable;
        myPath = new ArrayList<>();
    }

    protected Coordinate getLocation () {
        return getParent().getLocation();
    }

    protected void move (TimeDuration duration) {
        updateVelocities();
        double xChange = distance(getXVel().getValueProperty().get(), duration.getMillis());
        double yChange = distance(getYVel().getValueProperty().get(), duration.getMillis());
        move(getNextCoordinate(xChange, yChange));
    }

    protected void move (Coordinate coordinate) {
        getParent().setLocation(coordinate);
    }

    protected Coordinate getNextCoordinate (double xChange, double yChange) {
        return new Coordinate(getLocation().getX() + xChange,
                              getLocation().getY() + yChange);
    }

    protected double distance (double rate, double time) {
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
        attributes.add(mySpeed);
        attributes.add(myOrientation);
        attributes.add(myXVel);
        attributes.add(myYVel);
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

    private void setXVel (double newVel) {
        myXVel.setValue(newVel);
    }

    private void setYVel (double newVel) {
        myYVel.setValue(newVel);
    }

    private void updateVelocities () {
        setXVel(getSpeed() *
                Math.cos(myOrientation.getValueProperty().get()));
        setYVel(getSpeed() *
                Math.sin(myOrientation.getValueProperty().get()));
    }

    /**
     * the angle is stored in radians, all conversion of angles will occur before it is
     * presented to the user, and right after it is taken in by the user
     *
     * The X and Y velocities are only set according to this method and set speed now
     * 
     * @return
     */
    @Override
    public void setOrientation (double newAngle) {
        myOrientation.setValue(newAngle * DEGREES_TO_RADS);
        setXVel(Math.cos(newAngle * DEGREES_TO_RADS) * mySpeed.getValueProperty().get());
        setYVel(Math.sin(newAngle * DEGREES_TO_RADS) * mySpeed.getValueProperty().get());

    }
    @Override
    public void setOrientationFromTracker (double newAngle) {
        myOrientation.setValue(newAngle);
        setXVel(Math.cos(newAngle) * mySpeed.getValueProperty().get());
        setYVel(Math.sin(newAngle) * mySpeed.getValueProperty().get());

    }

    /**
     * this sets the speed and the X and Y velocities according to the current speed and angle.
     * 
     * The X and Y velocities are only set according to this method and set orientation now
     */
    @Override
    public void setSpeed (double newSpeed) {
        mySpeed.setValue(newSpeed * MULTIPLIER);
        setXVel(Math.cos(myOrientation.getValueProperty().get()) * newSpeed);
        setYVel(Math.sin(myOrientation.getValueProperty().get()) * newSpeed);
    }

    @Override
    public void setPath (List<Coordinate> path) {
        myPath = path;
    }

    @Override
    public List<Coordinate> getPath () {
        return myPath;
    }

    @Override
    public double getOrientation () {
        return myOrientation.getValueProperty().get();
    }

    @Override
    public double getSpeed () {
        return mySpeed.getValueProperty().get();
    }

    protected void setSpeedUnOriented (double speed) {
        mySpeed.getValueProperty().set(speed);
    }

    protected Positionable getParent () {
        return myParent;
    }
    


}
