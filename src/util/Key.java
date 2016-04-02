package util;

/**
 * Used to represent Key without JavaFX dependency
 * @author RyanStPierre
 *
 */
public class Key {

    private String myKeyCode;
    
    public Key (String identifier) {
        myKeyCode = identifier;
    }
    
    public boolean isEqual(String keyIdentifier)  {
        // TODO implement
        return myKeyCode == keyIdentifier;
       
    }
}
