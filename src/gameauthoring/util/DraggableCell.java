// This entire file is part of my masterpiece.
// Tommy Romano
package gameauthoring.util;

import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.levels.sprites.Draggable;
import javafx.scene.Node;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * For the masterpiece code, I decided to show off the Decorator Design Pattern I followed
 * to create the cell views for the front end. In the mean time, this code shows off various
 * skills I learned throughout the semester, including interfaces, generics, MVC, and lamda
 * functions.
 * 
 * So, we'll start with the draggable cell. This is one level up from the base profile cell.
 * This class begins by extending a normal profile cell view to be draggable, but not
 * actually set the actions. Because the actions are not set, I made the class abstract, so 
 * no user could accidentally implement it. Additionally, this class follows the decorator
 * design pattern by first overwriting the createSpriteCell method. It first creates the cell
 * as the super class would, and adds actions to this. I really liked this pattern because
 * I'm also taking Computer Networks, and many network protocols basically implement the same
 * thing. When moving a packet up from the Ethernet level to the IP level, you simply add on
 * a new header. Here, I am just taking the old cell and adding on actions. This is optimal
 * because instead of making one profile cell view for any game object, I just have to design
 * a smart hierarchy, and then choose a level of the hierarchy to use to represent a game object.
 * For example, to show an event, I would just show the general ProfileCellView, because I don't
 * want to drag it. This leaves the cell view open for any extension, but also capable of being
 * used on its own. Following the Liskov Subsitution principle, any of these cell views can
 * be swapped out for any other, and it would just change the functionality or look. 
 * @author Tommy
 *
 * @param <T> - What the cell actually stores, like a sprite
 */
public abstract class DraggableCell<T extends IProfilable> extends ProfileCellView<T>
        implements Draggable {
    
    private Node myTarget;
    protected static final String DRAG_STRING = "Item";

    /**
     * Override the set on drag detected to set the drag view and
     * set the drag actions (to be implemented later)
     */
    @Override
    public void setOnDragDetected (MouseEvent e, Node node) {
        Dragboard db = node.startDragAndDrop(TransferMode.COPY);
        db.setContent(createClipboard(DRAG_STRING));

        db.setDragView(getSpriteImage());

        myTarget.setOnDragOver(event -> setOnDragOver(event));
        myTarget.setOnDragDropped(event -> setOnDragDropped(event));
    }

    /**
     * Override the sprite node to add dragging actions to it
     */
    @Override
    protected Node createSpriteCell (T profile) {
        Node node = super.createSpriteCell(profile);
        setActions(node);
        return node;
    }

    /**
     * Sets the target of the dragging, or where it should
     * be dropped.
     * @param target - node where the cell should be dropped
     */
    protected void setTarget (Node target) {
        myTarget = target;
    }
}
