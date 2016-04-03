package graphics;

import gameplayer.IGraphicFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.beans.property.ObjectProperty;
import util.RGBColor;


public class Block extends SizeableGraphic {

    private DoubleProperty myWidth;
    private DoubleProperty myHeight;
    private ObjectProperty<RGBColor> myFill;

    public Block (double width, double height, RGBColor fill) {
        super(width, height);
        myFill = new SimpleObjectProperty<>(fill);
    }

    public ObjectProperty<RGBColor> getFillProeprty () {
        return myFill;
    }

    @Override
    public Node getVisualRepresentation (IGraphicFactory factory) {
        return factory.getVisual(this);
    }

}
