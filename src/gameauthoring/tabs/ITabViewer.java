// This entire file is part of my masterpiece.
// Dhrumil Patel

package gameauthoring.tabs;

import gameauthoring.util.Glyph;


/**
 * 
 * The ITabViewer is an interface that provides standardizes the behavior for constructing the
 * notion of a tab viewer. In this project the GameTabViewer, CharTabViewer, WaveTabViewer, and
 * SceneTabViewer classes
 * implement this interface. Although this interface alone isn't a representation of what I believe
 * is good code, this interface
 * is implemented in my TabViewFactory and utilizes the Builder design pattern because each
 * implementation of this interface
 * constructs separate notions of tabs with its respective view elements. The interface also
 * inherits from the Glpyh class which
 * provides the behavior to create the JavaFX node representation of the tab viewer.
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
