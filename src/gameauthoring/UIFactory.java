package gameauthoring;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

/**
 * Helpful factory to create common UI elements like
 * a button, combo box, header text or otherwise
 * @author Tommy
 *
 */
public interface UIFactory {
    
    Node createButton(String text, EventHandler<ActionEvent> action);
    
    Node createComboBox(List<String> text);
}
