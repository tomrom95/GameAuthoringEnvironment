package engine;

import javafx.collections.ObservableList;


/**
 * This interface represents a playable game, essentially exposing the necessary functionality to
 * execute a game, but not to edit it.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGamePlayable extends Updateable {

    /**
     * @return the object containing information about this game
     */
    IGameInformation getGameInformation ();

    /**
     * @return list of Drawable objects that a game contains
     */
    ObservableList<? extends Drawable> getDrawables ();

    /**
     * @return the global attributes for this game
     */
    ObservableList<IAttribute> getGlobalAttributes ();

}
