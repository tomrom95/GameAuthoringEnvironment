package engine;

import util.Key;


public class ControlKeys {

    Key myUpKey;
    Key myLeftKey;
    Key myRightKey;
    Key myDownKey;

    public ControlKeys (Key up, Key left, Key right, Key down) {
        myUpKey = up;
        myLeftKey = left;
        myRightKey = right;
        myDownKey = down;
    }

    public Key getUpKey () {
        return myUpKey;
    }
    
    public Key getLeftKey () {
        return myLeftKey;
    }
    
    public Key getRightKey () {
        return myRightKey;
    }
    
    public Key getDownKey () {
        return myDownKey;
    }

}
