package engine;

import java.util.ArrayList;
import java.util.List;
import interactionevents.IScreenEventFactory;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import interactionevents.ScreenEventFactory;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
    List<KeyIOEvent> queuedMouseEvents;
    List<MouseIOEvent> queuedKeyEvents;
    
    
    public IOInterpeter (Pane pane) {
        eventFactory = new ScreenEventFactory ();
        setUpListener(pane);
    }

    private void setUpListener (Pane pane) {
     
       pane.setOnMouseClicked(e -> eventFactory.interpretEvent(e));
       pane.setOnMouseReleased(e -> eventFactory.interpretEvent(e));
       pane.setOnKeyPressed(e -> eventFactory.interpretEvent(e));
       pane.setOnKeyReleased(e -> eventFactory.interpretEvent(e));
    }
   

    private void queue (MouseIOEvent setOnMouseClicked) {
        // TODO Auto-generated method stub
        
    }

    public List<IEffect> deQueueEvents () {
        List<IEffect> copy = new ArrayList<IEffect>(queuedEffects);
        queuedEffects.clear();
        return copy;
    }

}
