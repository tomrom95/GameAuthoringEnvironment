package engine.rendering;

import graphics.Block;
import graphics.ImageGraphic;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Class that makes graphics to desired size (given by width and height)
 * @author RyanStPierre
 *
 */
public class ScaleFactory extends GraphicFactory{

    private double myWidth;
    private double myHeight;
    
    public ScaleFactory (double width, double height) {
        super();
        myWidth = width;
        myHeight = height;
    }
    
    @Override
    public Node getVisual (Block block) {
        Rectangle rect = super.getRectangle(block);
        rect.setWidth(myWidth);
        rect.setHeight(myHeight);
        return rect;
    }
    
    @Override
    public Node getVisual (ImageGraphic imageGraphic) {
        ImageView imageView = super.getImageView(imageGraphic);
        imageView.resize(myWidth, myHeight);
        return imageView;
    }
}
