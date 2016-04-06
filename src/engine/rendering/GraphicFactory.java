package engine.rendering;

import java.util.List;
import java.util.ResourceBundle;
import engine.IAttribute;
import graphics.Block;
import graphics.ImageGraphic;
import graphics.TextGraphic;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.RGBColor;

/**
 * Defines uniform functionality between scale and bound factory
 * Returns nodes with no notion of size
 * @author RyanStPierre
 *
 */
public abstract class GraphicFactory implements IGraphicFactory {

    private static final int ALPHA = 1;
    private static final String DEFAULT_PATH = "resource/DefaultGraphics";
    
    ResourceBundle myResources;
    
    public GraphicFactory () { 
        myResources = ResourceBundle.getBundle(DEFAULT_PATH);
    }
    
   
    protected Rectangle getRectangle (Block block) {
        Rectangle rect = new Rectangle();
        rect.setFill(rgbToColor(block.getFillProeprty().get()));
        return rect;
    }
    
    protected ImageView getImageView (ImageGraphic imageGraphic) {
        Image image = new Image(imageGraphic.getUrlProperty().get());
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public Node getVisual (TextGraphic textGraphic) {
        Text text = getText(textGraphic.getTextProperty().get());
        text.setFont(Font.font(textGraphic.getFamilyProperty().get(), textGraphic.getFontSizeProperty().get()));
        text.setFill(rgbToColor(textGraphic.getFillProperty().get()));
        return text;
    }
    
    private Color rgbToColor (RGBColor rgb) {
        Color color = new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), ALPHA);
        return color;
        
    }

    @Override
    public VBox renderHUD (List<IAttribute> attributes) {
        String spacing = myResources.getString("DefaultVBoxSpacing");
        VBox vbox = new VBox(Double.parseDouble(spacing));
        attributes.forEach(a -> addToVBox(vbox, a));
        return vbox;
    }

    private void addToVBox (VBox vbox, IAttribute attribute) {
        Text title = getText(attribute.getType().toString());
        Text value = getText("" + attribute.getValueProperty().get());
        vbox.getChildren().addAll(title, value);
    }
    
    private Text getText (String str) {
        return new Text(str);
    }

}
