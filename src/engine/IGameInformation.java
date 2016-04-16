package engine;

import javafx.beans.property.StringProperty;


/**
 * This interface represents high-level information about a game, such as its name and author.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGameInformation {

    /**
     * @return the string property of the author of this game
     */
    StringProperty getAuthorProperty ();

    /**
     * @return the string property of the date this game was created
     */
    StringProperty getDateCreatedProperty ();

    /**
     * @return the string property of the name of this game
     */
    StringProperty getNameProperty ();
}
