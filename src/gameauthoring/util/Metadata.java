package gameauthoring.util;

/**
 *
 * A class that defines metadata about another object
 * 
 * @author Jeremy Schreck
 *
 */

public class Metadata {

    private String myTitleKey;

    public Metadata (String titleKey) {
        myTitleKey = titleKey;
    }

    /**
     * Get a key that can be used to retrieve a title from a resource bundle
     * 
     * @return The title key
     */
    public String getTitleKey () {
        return myTitleKey;
    }

}
