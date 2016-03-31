package engine;

import javafx.collections.ObservableList;


/**
 * This interface represents a component of a game that manages the global attributes that a game
 * can contain
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGlobalState extends Updateable, Affectable {

    /**
     * @return the observable list containing the global attributes
     */
    ObservableList<IAttribute> getAttributes ();

}
