package engine.definitions.concrete;

import util.Coordinate;


//
public class LocationDefinition {

    private double myX;
    private double myY;

    public Coordinate create () {
        return new Coordinate(myX, myY);
    }

    public void setX (double x) {
        myX = x;
    }

    public void setY (double y) {
        myY = y;
    }

}
