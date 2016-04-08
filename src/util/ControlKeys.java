package util;

/**
 * This class serves to stores cardinal directions specified by the user for the game or a sprite.
 *
 *
 */
public class ControlKeys {

    private Key myUpKey;
    private Key myLeftKey;
    private Key myRightKey;
    private Key myDownKey;

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
