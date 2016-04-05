package gameauthoring.levels;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.image.Image;


/**
 * This is the game screen for each level.
 * 
 * @author Jin An
 *
 */
public interface ILevelView {

    // Has "add background" button

    Image getBackGround ();

    List<Node> getNodes ();

    void setNodeAction ();
}
