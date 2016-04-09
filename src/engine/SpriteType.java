package engine;

/**
 * This class represents the type of a sprite, with the appropriate implementation to allow for
 * equality tests
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 * @author Jeremy Schreck
 *
 */
public class SpriteType {

    private String myName;

    public SpriteType (String name) {
        myName = name;
    }

    public String getName () {
        return myName;
    }
    
    public void setName (String name) {
        this.myName = name;
    }
}
