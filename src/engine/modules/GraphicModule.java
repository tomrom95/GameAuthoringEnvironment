package engine.modules;


import engine.effects.DefaultAffectable;
import engine.rendering.IGraphicFactory;
import graphics.IGraphic;
import javafx.scene.Node;



/**
 * This class implements IGraphicModule and serves as the manages and manipulates the visual
 * properties of a sprite.
 *
 */

public class GraphicModule extends DefaultAffectable implements IGraphicModule {

    private IGraphic myGraphic;

    public GraphicModule (IGraphic graphic) {
        myGraphic = graphic;
    }

    @Override
    public IGraphic getGraphic () {
        return myGraphic;
    }

    @Override
    public Node getVisualRepresentation (IGraphicFactory graphicFactory) {
        return myGraphic.getVisualRepresentation(graphicFactory);
    }

  

}
