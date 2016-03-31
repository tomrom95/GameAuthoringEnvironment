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

    StringProperty getAuthorProperty ();

    StringProperty getDateCreatedProperty ();

    StringProperty getNameProperty ();
}
