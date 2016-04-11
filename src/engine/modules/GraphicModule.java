package engine.modules;

import java.util.List;
import engine.IAttribute;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.rendering.IGraphicFactory;
import graphics.IGraphic;
import javafx.scene.Node;
import util.TimeDuration;


/**
 * This class implements IGraphicModule and serves as the manages and manipulates the visual
 * properties of a sprite.
 *
 */

public class GraphicModule implements IGraphicModule {

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

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

    @Override
    public void applyEffect (IEffect effect) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<IAttribute> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

}
