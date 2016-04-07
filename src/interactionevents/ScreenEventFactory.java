package interactionevents;

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
    public MouseIOEvent interpretEvent (MouseEvent event) {
        InputType type;
        try {
            type = convertType(event.getEventType().toString());
        }
        catch (ClassNotFoundException error) {
            type = InputType.MOUSE_CLICKED;
        }
        MouseIOEvent mouse = new MouseIOEvent(type, event.getX(), event.getY());
        return mouse;
    }

    @Override
    public KeyIOEvent interpretEvent (KeyEvent event) {
        Key key = new Key(event.getCode().getName());
        InputType type;
        try {
            type = convertType(event.getEventType().toString());
        }
        catch (ClassNotFoundException e) {
            type = InputType.KEY_PRESSED;
        }
        KeyIOEvent keyEvent = new KeyIOEvent(type, key);
        return keyEvent;

    }

    /**
     * Converts the JavaFX ActionEvent into an InputType, a class that is understood by the
     * GameEngine.
     * 
     * @param str string representing the ActionEvent
     * @return InputType that corresponds to the IOEvent that the GameEngine interprets
     * @throws ClassNotFoundException
     */
    private InputType convertType (String str) throws ClassNotFoundException {

        Class inputClass = Class.forName(InputType.class.getName());
        InputType o = (InputType) Enum.valueOf(inputClass, str);
        return o;

    }
}
