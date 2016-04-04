package engine;

import gameplayer.IGraphicFactory;
import graphics.IGraphic;
import javafx.scene.Node;


public class GraphicModule implements IGraphicModule {

    private IGraphic myGraphic;

    public GraphicModule (IGraphic graphic) {
        myGraphic = graphic;
    }
    
    public IGraphic getGraphic () {
        return myGraphic;
    }

    @Override
    public Node getVisualRepresentation (IGraphicFactory graphicFactory) {
        return myGraphic.getVisualRepresentation(graphicFactory);
    }

}
