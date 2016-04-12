package gameauthoring.levels.sprites;

import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 * Interface  for view elements to implement in order to be draggable.
 * Has some default helper methods to help out with repetitive javafx
 * code
 * @author Tommy
 *
 */
public interface Draggable{
    
    /**
     * Sets the drag actions of the source
     * @param source
     */
    default void setActions(Node source) {
        source.setOnDragDetected(e -> setOnDragDetected(e, source));
    }
    
    /**
     * Default method to create a clipboard holding a simple name.
     * Necessary for dragging sprites.
     * @param name
     * @return
     */
    default ClipboardContent createClipboard(String name) {
        ClipboardContent content = new ClipboardContent();
        content.putString(name);
        return content;
    }
    
    public void setOnDragDetected(MouseEvent e, Node node);
    
    public void setOnDragOver(DragEvent e);
    
    public void setOnDragDropped(DragEvent e);

}
