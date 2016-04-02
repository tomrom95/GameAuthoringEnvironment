package gameplayer;

import java.util.List;
import engine.IAttribute;
import graphics.TextGraphic;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.Block;

public class GraphicFactory implements IGraphicFactory {

    @Override
    public Node getVisual (Block block) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getVisual (Image image) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getVisual (TextGraphic textGraphic) {
        Text text = new Text(textGraphic.getText());
        //TODO font size
        return text;
    }

    @Override
    public VBox renderHUD (List<IAttribute> attributes) {
        // TODO Auto-generated method stub
        return null;
    }

}
