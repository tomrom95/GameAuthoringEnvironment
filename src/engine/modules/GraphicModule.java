package engine.modules;

import engine.effects.DefaultAffectable;
import engine.events.EventType;
import engine.events.GameEvent;
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
    private boolean myVisibility;

    public GraphicModule (IGraphic graphic) {
        myGraphic = graphic;
        myVisibility = true;
    }

    @Override
    public IGraphic getGraphic () {
        return myGraphic;
    }

    @Override
    public Node getVisualRepresentation (IGraphicFactory graphicFactory) {
        return myGraphic.getVisualRepresentation(graphicFactory);
    }

    @Override
    public void registerEvent (GameEvent event) {
        if (event.getEventType().equals(EventType.VISIBLE)) {
            myVisibility = true;
        }
        if (event.getEventType().equals(EventType.INVISIBLE)) {
            myVisibility = false;
        }

    }

    @Override
    public Boolean isVisible () {
        return myVisibility;
    }

}
