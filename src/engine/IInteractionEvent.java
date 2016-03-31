package engine;

import util.Key;

public interface IInteractionEvent {

    boolean isKeyInput (); 
    
    boolean isClickInput (); 
    
    Key getKey ();
}
