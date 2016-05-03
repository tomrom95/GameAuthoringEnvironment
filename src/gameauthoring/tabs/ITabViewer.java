package gameauthoring.tabs;

import gameauthoring.util.Glyph;


/**
 * An interface for three main tab view classes
 * Implemented by GameTabViewer, CharTabViewer, and SceneTabViewer
 *
 * @author Dhrumil
 * @author Jin An
 *
 *
 */
public interface ITabViewer extends Glyph {

    /**
     * initializes the layout and display each tab
     */
    void init ();

    /**
     * Rescales the layout based on the given parameters
     *
     * @param width
     * @param height
     */
    void rescale (double width, double height);
}
