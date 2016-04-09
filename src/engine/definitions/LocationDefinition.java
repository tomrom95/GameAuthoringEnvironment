package engine.definitions;

import util.Coordinate;

public class LocationDefinition implements IDefinition {

    private double myX;
    private double myY;
    
    public Coordinate create () {
        return new Coordinate (myX, myY);
    }
    
    public void setX (int x) {
        myX = x;
    }
    
    public void setY (int y) {
        myY = y;
    }
}
