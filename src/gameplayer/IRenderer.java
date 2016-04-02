package gameplayer;

import engine.Drawable;
import javafx.collections.ObservableList;

/**
 * This interface represents an object that can handle the rendering a game and all of its
 * appropriate visuals
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IRenderer {

    void renderDrawables(ObservableList<Drawable> drawables);
}
