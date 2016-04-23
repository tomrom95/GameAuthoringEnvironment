package gameauthoring.waves;

import java.util.ResourceBundle;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import gameauthoring.creation.cellviews.WaveDragCell;
import gameauthoring.util.UIFactory;
import graphics.IGraphic;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import util.StringParser;


public class ListGraphicFactory {

    ResourceBundle myBundle = ResourceBundle.getBundle("defaults/list_graphic_factory");
    UIFactory myFactory = new UIFactory();

    public Node createGraphic (WaveBlockDefinition item) {
        HBox hbox = new HBox(toDouble(myBundle.getString("Buffer")));
        hbox.getChildren().add(SpriteToImage(item));
        hbox.getChildren().add(myFactory.createLabel(Integer.toString(item.getCount())));
        hbox.getChildren().add(myFactory.createLabel(Double.toString(item.getGap())));
        return hbox;
    }

    private Node SpriteToImage (WaveBlockDefinition item) {
        IGraphic graphic = item.getSprite().getProfile().getImage();
        return myFactory.createImageView(graphic, getImageWidth(), getImageHeight());
    }

    private double getImageHeight () {
       return toDouble(myBundle.getString("ImageHeight"));
    }

    private double toDouble (String string) {
        return new StringParser().parseDouble(string);
    }

    private double getImageWidth () {
        return toDouble(myBundle.getString("ImageWidth"));
    }

    public Image getImage (WaveDragCell waveDragCell) {
        return new Image(myBundle.getString("ImageURL"));
    }

}
