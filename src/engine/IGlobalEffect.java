package engine;

import util.Key;

public interface IGlobalEffect {

    boolean isKeyInput (); 
    
    boolean isClickInput (); 
    
    Key getKey ();
}
