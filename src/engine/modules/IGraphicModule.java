package engine.modules;

import engine.rendering.IGraphicFactory;
import graphics.IGraphic;
import javafx.scene.Node;


/**
 * This interface represents the component of a sprite that will handle its visual properties
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGraphicModule extends IModule {

    Node getVisualRepresentation (IGraphicFactory factory);

    IGraphic getGraphic ();

    /**
     * Flag checkable by the renderer to support visibility of sprites as a function
     * of visible invisible GameEvents
     *
     * @return true if visible, false if invisible
     */
    Boolean isVisible ();

}
