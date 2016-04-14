package engine.rendering;

import graphics.Block;
import graphics.ImageGraphic;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


/**
 * Scales based on specified user definition of the graphic (draws graphic as is - defined by its
 * bounds)
 * 
 * @author RyanStPierre
 *
 */
public class UnscaledFactory extends GraphicFactory {

    @Override
    public Node getVisual (Block block) {
        Rectangle rect = super.getRectangle(block);
        rect.setWidth(block.getWidth().get());
        rect.setHeight(block.getHeight().get());
        return rect;
    }

    @Override
    public Node getVisual (ImageGraphic imageGraphic) {
        ImageView imageView = super.getImageView(imageGraphic);
        imageView.setFitWidth(imageGraphic.getWidth().get());
        imageView.setFitHeight(imageGraphic.getHeight().get());
        return imageView;
    }
}
