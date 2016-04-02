package graphics;

import gameplayer.IGraphicFactory;
import javafx.scene.Node;


public interface IGraphic {

    Node getVisualRepresentation (IGraphicFactory factory);
}
