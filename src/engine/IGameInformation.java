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
 * @author Jin An
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

    /**
     * @return SplashScreen URL of the ImageGraphic object of the game
     */
    String getSplashScreenURL ();

    /**
     * set the author of the game
     *
     * @param author
     */
    void setAuthor (String author);

    /**
     * set the date created of the game
     *
     * @param date
     */
    void setDateCreated (String date);

    /**
     * set the name of the game
     *
     * @param name
     */
    void setName (String name);

    /**
     * set the splashscreen of the game
     *
     * @param splashScreen
     */
    void setSplashScreen (String url);

    StringProperty getSplashScreenURLProperty ();

}
