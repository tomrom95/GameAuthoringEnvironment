package engine;

import java.util.List;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


public class PathFollowMover implements IMovementModule {

    private ObjectProperty<IAttribute> myXVel;
    private ObjectProperty<IAttribute> myYVel;
    private ObjectProperty<Coordinate> myLocation;
    private ObjectProperty<IAttribute> mySpeed;
    private List<Coordinate> myPoints;
    private int myNextDestination;

    public PathFollowMover (ObjectProperty<Coordinate> location,
                            double speed,
                            List<Coordinate> points) {
        myXVel = new SimpleObjectProperty<>(new Attribute());
        myYVel = new SimpleObjectProperty<>(new Attribute());
        myLocation = location;
        mySpeed = new SimpleObjectProperty<>(new Attribute(speed));
        myPoints = points;
        myNextDestination = 0;

    }

    @Override
    public void update (TimeDuration duration) {
        updateIndex();
        adjustVectors();
        double xChange = distance(myXVel.get().getValueProperty().get(), duration.getMillis());
        double yChange = distance(myYVel.get().getValueProperty().get(), duration.getMillis());
        myLocation.set(getNextCoordinate(xChange, yChange));

    }

    private void updateIndex () {
        double xDiff = myPoints.get(myNextDestination).getX() - myLocation.get().getX();
        double yDiff = myPoints.get(myNextDestination).getY() - myLocation.get().getY();
        if (withinRange(xDiff) && withinRange(yDiff)) {
            if(!lastPoint()) {
                myNextDestination++;
            }
        }
    }

    private boolean lastPoint () {
        return myNextDestination == myPoints.size()-1;
    }

    private boolean withinRange (double input) {
        return Math.abs(input)<5;
    }

    private Coordinate getNextCoordinate (double xChange, double yChange) {
        return new Coordinate(myLocation.get().getX() + xChange, myLocation.get().getY() + yChange);
    }

    private double distance (double rate, double time) {
        return rate * time;
    }

    private void adjustVectors () {
      
        double xDiff = myPoints.get(myNextDestination).getX() - myLocation.get().getX();
        double yDiff = myPoints.get(myNextDestination).getY() - myLocation.get().getY();
        //System.out.println("Heading: " + myPoints.get(myNextDestination).getX() + " " + myPoints.get(myNextDestination).getY());
        //System.out.println("My Location: "  + myLocation.get().getX() + " " + myLocation.get().getY());
        //System.out.println(xDiff + " " + yDiff);
        double constant = Math.sqrt(square(mySpeed.get().getValueProperty().get())  / (square(xDiff) + square(yDiff)));
        if(!isZero(xDiff)) {
            myXVel.get().getValueProperty().set(xDiff * constant);
        } else {
            myXVel.get().getValueProperty().set(0);
        }
        if(!isZero(yDiff)) {
            myYVel.get().getValueProperty().set(yDiff * constant);
        } else {
            myYVel.get().getValueProperty().set(0);
        }

    }

    private boolean isZero(double input) {
        return Math.abs(input)<.0000000001;
    }
    private double square (double input) {
        return input * input;
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
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Coordinate> getLocationProperty () {
        return myLocation;
    }

}
