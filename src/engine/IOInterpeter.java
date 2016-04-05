package engine;

import java.util.ArrayList;
import java.util.List;
import interactionevents.IScreenEventFactory;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import interactionevents.ScreenEventFactory;
import javafx.scene.Scene;
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

    private IScreenEventFactory myEventFactory;
    private List<KeyIOEvent> myQueuedKeyEvents;
    private List<MouseIOEvent> myQueuedMouseEvents;

    public IOInterpeter (Scene scene, Pane pane) {
        myEventFactory = new ScreenEventFactory();
        myQueuedKeyEvents = new ArrayList<>();
        myQueuedMouseEvents = new ArrayList<>();
        setUpListener(scene, pane);
    }

    private void setUpListener (Scene scene, Pane pane) {

        pane.setOnMouseClicked(e -> queue(myEventFactory.interpretEvent(e)));
        scene.setOnKeyPressed(e -> queue(myEventFactory.interpretEvent(e)));
        scene.setOnKeyReleased(e -> queue(myEventFactory.interpretEvent(e)));
    }

    private void queue (MouseIOEvent event) {
        myQueuedMouseEvents.add(event);
    }

    private void queue (KeyIOEvent event) {
        myQueuedKeyEvents.add(event);
    }

    public List<MouseIOEvent> deQueueMouseEvents () {
        List<MouseIOEvent> copy = new ArrayList<>(myQueuedMouseEvents);
        myQueuedMouseEvents.clear();
        return copy;
    }

    public List<KeyIOEvent> deQueueKeyEvents () {
        List<KeyIOEvent> copy = new ArrayList<>(myQueuedKeyEvents);
        myQueuedKeyEvents.clear();
        return copy;
    }

}
