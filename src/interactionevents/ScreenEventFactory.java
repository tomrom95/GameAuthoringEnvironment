package interactionevents;




import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.Key;


public class ScreenEventFactory implements IScreenEventFactory  {

    @Override
    public MouseIOEvent interpretEvent (MouseEvent event) {
        // TODO Auto-generated method stub
        MouseIOEvent mouse = new MouseIOEvent(InputType.MOUSE_CLICKED, event.getX(), event.getY());
        System.out.println(mouse);
        return mouse;
    }

    @Override
    public KeyIOEvent interpretEvent (KeyEvent event) {
        Key key = new Key (event.getCode().getName());
        KeyIOEvent keyEvent = new KeyIOEvent(InputType.KEY_PRESSED, key);
        System.out.println(keyEvent);
        return keyEvent;
        
    }
}
    
    


