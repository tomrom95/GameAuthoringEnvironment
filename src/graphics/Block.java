package graphics;

import engine.rendering.IGraphicFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import util.RGBColor;


/**
 * This class serves as an abstraction of a JavaFX image that stores the height and width of an
 * object
 * and a RGB color.
 *
 */
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
