package engine;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import util.Block;
import util.TextGraphic;


public interface IGraphicFactory {

    Node getVisual (Block block);

    Node getVisual (Image image);

    Node getVisual (TextGraphic text);

    VBox renderHUD (List<IAttribute> attributes);

}
