package graphics;

import gameplayer.IGraphicFactory;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;


/**
 * This interface holds the notion of storing a sprites front-end characteristics as a graphic to
 * remove the JavaFX dependency and manipulate in the back-end
 * 
 */
public interface IGraphic {

    Node getVisualRepresentation (IGraphicFactory factory);

    DoubleProperty getWidth ();

    DoubleProperty getHeight ();
}
