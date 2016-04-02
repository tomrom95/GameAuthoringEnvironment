package interactionevents;

/**
 * Used to represent Mouse Events without JavaFX dependency
 * @author RyanStPierre
 *
 */
public class MouseIOEvent extends IOEvent{

    private double myX;
    private double myY;
    
    public MouseIOEvent (InputType type, double x, double y) {
        super(type);
        myX = x;
        myY = y;
    }
    
    public double getX () { 
        return myX;
    }
    
    public double getY () {
        return myY;
    }

}
