package engine;

import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;

public interface IOAffectable extends Updateable {

    /**
     * Respond appropriately to a global key interaction event
     * 
     * @param keyEvent to respond to
     */
    void registerKeyEvent (KeyIOEvent keyEvent);
    
    /**
     * Respond appropriately to a global mouse interaction event
     * 
     * @param mouseEvent to respond to
     */
    void registerMouseEvent (MouseIOEvent mouseEvent);
    
}
