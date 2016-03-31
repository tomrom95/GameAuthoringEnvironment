package engine;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import util.Block;
import util.TextGraphic;


/**
 * This interface represents a factory that will convert back-end structures into their visual
 * JavaFX representations.
 * 
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGraphicFactory {

    /**
     * @param block to convert
     * @return Node representing this block
     */
    Node getVisual (Block block);

    /**
     * 
     * @param image to convert
     * @return Node representing this Image
     */
    Node getVisual (Image image);

    /**
     * @param text to be converted
     * @return Node representing this text
     */
    Node getVisual (TextGraphic text);

    /**
     * @param attributes to be converted
     * @return VBox containing the visual representation of these attributes. 
     */
    VBox renderHUD (List<IAttribute> attributes);

}
