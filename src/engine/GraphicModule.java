package engine;

import gameplayer.IGraphicFactory;
import graphics.IGraphic;
import javafx.scene.Node;

public class GraphicModule implements IGraphicModule {

    IGraphic myGraphic;
    
    public GraphicModule(IGraphic graphic) {
        myGraphic = graphic;
    }
    
    @Override
    public Node getVisualRepresentation (IGraphicFactory graphicFactory) {
       return myGraphic.getVisualRepresentation(graphicFactory);
    }

}
