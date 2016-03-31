package engine;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


/**
 * Takes a JavaFX ActionEvent from the screen and returns an effect that can be handled by the game
 * engine
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 */
public interface IScreenEventFactory {

    /**
     * @param event to convert
     * @return an effect that represents the given mouse event
     */
    IGlobalEffect interpretEvent (MouseEvent event);

    /**
     * @param event to convert
     * @return an effect that represents the given key event
     */
    IGlobalEffect interpretEvent (KeyEvent event);


}
