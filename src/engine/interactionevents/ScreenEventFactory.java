package engine.interactionevents;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.Key;


/**
 * This class implements IScreenEventFactory and handles effects from the game and converts them to
 * be interpreted by the game engine.
 *
 *
 */

public class ScreenEventFactory implements IScreenEventFactory {
    
    @Override
    public MouseIOEvent interpretEvent (MouseEvent event, double xscale, double yscale) {
        InputType type = convertType(event.getEventType().toString());
        MouseIOEvent mouse = new MouseIOEvent(type, event.getX()/xscale, event.getY()/yscale);
        return mouse;
    }

    @Override
    public KeyIOEvent interpretEvent (KeyEvent event) {
        Key key = new Key(event.getCode().getName());
        InputType type = convertType(event.getEventType().toString());
        KeyIOEvent keyEvent = new KeyIOEvent(type, key);
        return keyEvent;

    }

    /**
     * Converts the JavaFX ActionEvent into an InputType, a class that is understood by the
     * GameEngine.
     *
     * @param str string representing the ActionEvent
     * @return InputType that corresponds to the IOEvent that the GameEngine interprets
     */
    private InputType convertType (String str) {
        return InputType.valueOf(str);
    }
}
