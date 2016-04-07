package engine.interactionevents;

import util.Key;

/**
 * Used to represent key events without JavaFX dependencies
 * @author RyanStPierre
 *
 */
public class KeyIOEvent extends IOEvent {

    private Key myKey;
    
    public KeyIOEvent (InputType type, Key key) {
        super(type);
        myKey = key;
    }
    
    public Key getKey ()  {
        return myKey;
    }

}
