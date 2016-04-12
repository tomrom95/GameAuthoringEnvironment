package gameauthoring.tabs;

import gameauthoring.util.Glyph;


/**
 * An interface for three main tab view classes
 * Implemented by GameTabViewer, CharTabViewer, and SceneTabViewer
 * 
 * @author Jin An
 *
 */
public interface ITabViewer extends Glyph {

    /**
     * initializes the layout and display each tab
     */
    void init ();
}
