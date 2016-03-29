package util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Block {

    // TODO finish

    private DoubleProperty myWidth;
    private DoubleProperty myHeight;

    public Block (double width, double height) {
        myWidth = new SimpleDoubleProperty(width);
        myHeight = new SimpleDoubleProperty(height);
    }

    public DoubleProperty getWidthProperty () {
        return myWidth;
    }

    public DoubleProperty getHeightProperty () {
        return myHeight;
    }

}
