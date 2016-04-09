package gameauthoring;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;

/**
 * Helpful factory to create common UI elements like
 * a button, combo box, header text or otherwise
 * @author Tommy
 *
 */
public class UIFactory {
    
    public Node createButton(String text, EventHandler<ActionEvent> action){
        // TODO
        return null;
    }
    
    public Node createComboBox(List<String> text){
        //TODO
        return null;
    }
    
    public Image getImageFromNode(Node node) {
        return node.snapshot(new SnapshotParameters(), null);
    }
}
