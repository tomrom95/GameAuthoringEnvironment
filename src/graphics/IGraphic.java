package graphics;

import gameplayer.IGraphicFactory;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;


public interface IGraphic {

    Node getVisualRepresentation (IGraphicFactory factory);

    DoubleProperty getWidth ();

    DoubleProperty getHeight ();
}
