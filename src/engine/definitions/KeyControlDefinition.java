package engine.definitions;

import util.ControlKeys;
import util.Key;


/**
 * This class represents the definition for a user key input control. Handles the specification of
 * which keys correspond to actions
 *
 */
public class KeyControlDefinition {

    private String myUp;
    private String myLeft;
    private String myRight;
    private String myDown;

    public ControlKeys create () {
        return new ControlKeys(new Key(myUp), new Key(myLeft), new Key(myRight), new Key(myDown));
    }

    public void setUp (String up) {
        myUp = up;
    }

    public void setDown (String down) {
        myDown = down;
    }

    public void setLeft (String left) {
        myLeft = left;
    }

    public void setRight (String right) {
        myRight = right;
    }

    public String getUp () {
        return myUp;
    }

    public String getDown () {
        return myDown;
    }

    public String getLeft () {
        return myLeft;
    }

    public String getRight () {
        return myRight;
    }
}
