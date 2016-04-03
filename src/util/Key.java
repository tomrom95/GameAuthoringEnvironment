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
    
    public boolean isEqual(Key key)  {
        // TODO implement
        return myKeyCode == key.getKeyCode();
       
    }
   
    //TODO delete 
    
    private String getKeyCode () {
        return myKeyCode;
    }
}
