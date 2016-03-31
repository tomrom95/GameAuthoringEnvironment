package gameauthoring;

import java.util.function.Consumer;

/**
 * Displays the list of created objects on the side. Has
 * a button to add a new object to it. As a default, it's
 * blank.
 * @author Tommy
 *
 */
public interface ListViewDisplayer extends Glyph {
    
    /**
     * For use of the controller to set what happens
     * when a user hits "create"
     * @param action
     */
    void setCreateAction(Consumer<?> action);

}
