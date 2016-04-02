package interactionevents;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.Key;


public class ScreenEventFactory implements IScreenEventFactory {

    @Override
    public MouseIOEvent interpretEvent (MouseEvent event) {
        InputType type;
        try {
            type = convertType(event.getEventType().toString());
        }
        catch (ClassNotFoundException e) {
            type = InputType.MOUSE_CLICKED;
        }
        MouseIOEvent mouse = new MouseIOEvent(type, event.getX(), event.getY());
        return mouse;
    }

    @Override
    public KeyIOEvent interpretEvent (KeyEvent event) {
        Key key = new Key(event.getCode().getName());
        KeyIOEvent keyEvent = new KeyIOEvent(InputType.KEY_PRESSED, key);
        return keyEvent;

    }
    
    private InputType convertType (String str) throws ClassNotFoundException {

        Class c = Class.forName(InputType.class.getName());
        InputType o = (InputType) Enum.valueOf(c, str);
        return o;

    }
}
