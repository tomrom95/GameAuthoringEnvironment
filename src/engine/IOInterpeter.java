package engine;

import java.util.ArrayList;
import java.util.List;
import interactionevents.IScreenEventFactory;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import interactionevents.ScreenEventFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


/**
 * Watches for user interaction with the game screen and converts to the proper effects to be passed
 * to the game engine
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 */

public class IOInterpeter {

    IScreenEventFactory eventFactory;
    List<KeyIOEvent> queuedKeyEvents;
    List<MouseIOEvent> queuedMouseEvents;
    
    
    public IOInterpeter (Pane pane) {
        eventFactory = new ScreenEventFactory ();
        queuedKeyEvents = new ArrayList<>();
        queuedMouseEvents = new ArrayList<>();
        setUpListener(pane);
    }

    private void setUpListener (Pane pane) {
       pane.requestFocus();
       pane.setOnMouseClicked(e -> queue(eventFactory.interpretEvent(e)));
       pane.setOnKeyPressed(e -> queue(eventFactory.interpretEvent(e)));
       pane.setOnKeyReleased(e -> queue(eventFactory.interpretEvent(e)));
    }
   

    private void queue (MouseIOEvent event) {
        queuedMouseEvents.add(event);
    }
    
    private void queue (KeyIOEvent event) {
        queuedKeyEvents.add(event);
    }

    public List<MouseIOEvent> deQueueMouseEvents () {
        List<MouseIOEvent> copy = new ArrayList<>(queuedMouseEvents);
        queuedMouseEvents.clear();
        return copy;
    }
    
    public List<KeyIOEvent> deQueueKeyEvents () {
        List<KeyIOEvent> copy = new ArrayList<>(queuedKeyEvents);
        queuedKeyEvents.clear();
        return copy;
    }
    
    

}
