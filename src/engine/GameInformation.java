package engine;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Stores the info of the Game
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 */

public class GameInformation implements IGameInformation {

    private StringProperty myTitle = new SimpleStringProperty();
    private StringProperty myAuthor = new SimpleStringProperty();
    private StringProperty myDateCreated = new SimpleStringProperty();

    public GameInformation (String title, String author, String dateCreated) {
        myTitle.set(title);
        myAuthor.set(author);
        myDateCreated.set(dateCreated);
    }

    @Override
    public StringProperty getNameProperty () {
        return myTitle;
    }

    @Override
    public StringProperty getAuthorProperty () {
        return myAuthor;
    }

    @Override
    public StringProperty getDateCreatedProperty () {
        return myDateCreated;
    }
}
