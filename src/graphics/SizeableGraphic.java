package graphics;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

abstract public class SizeableGraphic implements IGraphic{

    private DoubleProperty myWidth;
    private DoubleProperty myHeight;
    
    public SizeableGraphic (double width, double height) { 
        myWidth = new SimpleDoubleProperty(width);
        myHeight = new SimpleDoubleProperty(height);
    }
    
    public DoubleProperty getWidth () {
        return myWidth;
    }

    public DoubleProperty getHeight () {
        return myHeight;
    }
    
}
