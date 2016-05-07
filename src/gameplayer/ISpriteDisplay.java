package gameplayer;

import engine.Drawable;
import javafx.scene.control.Button;


/**
 * Interface for defining the relationship between the Sprite display view and controller
 *
 * @author RyanStPierre
 *
 */
public interface ISpriteDisplay {

    /**
     * Gives the proper drawable for the view to represent
     *
     * @param sprite to be drawn
     */
    void populate (Drawable drawn);

    /**
     * Allows the controller to set the desired action
     *
     * @return
     */
    Button getUpgradeButton ();

    /**
     * Clears the view
     */
    void clear ();
}
