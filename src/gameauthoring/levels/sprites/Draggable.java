// This entire file is part of my masterpiece.
// Tommy Romano
package gameauthoring.levels.sprites;

import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;


/**
 * Interface for view elements to implement in order to be draggable.
 * This has some default helper methods to help out with repetitive javafx
 * code.
 * 
 * Since dragging has some very JavaFX specific actions, this does have to
 * rely on JavaFX objects. However, this still leaves it very open to
 * implement in any way. The on screen sprites actually use the same
 * interface to move around on the screen.
 *
 * @author Tommy
 * @author Jin An
 *
 */
public interface Draggable {

    /**
     * Sets the drag actions of the source
     *
     * @param source
     */
    default void setActions (Node source) {
        source.setOnDragDetected(e -> setOnDragDetected(e, source));
    }

    /**
     * Default method to create a clipboard holding a simple name.
     * Necessary for dragging sprites.
     *
     * @param name
     * @return
     */
    default ClipboardContent createClipboard (String name) {
        ClipboardContent content = new ClipboardContent();
        content.putString(name);
        return content;
    }

    /**
     * What to do when the cell is dropped
     * @param e - the drag mouse event
     * @param node - node that was dropped
     */
    public void setOnDragDetected (MouseEvent e, Node node);

    /**
     * What to do when the cell is dragged over the 
     * target
     * @param e - the drag mouse event
     */
    public void setOnDragOver (DragEvent e);

    /**
     * What to do when the cell is actually dropped
     * @param e - the drag mouse event
     */
    public void setOnDragDropped (DragEvent e);

}
