package gameauthoring.waves;

import java.util.ResourceBundle;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import gameauthoring.creation.cellviews.WaveDragCell;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.UIFactory;
import graphics.IGraphic;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import splash.LocaleManager;
import util.StringParser;


public class ListGraphicFactory {

    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/list_graphic_factory");
    private ResourceBundle myLang =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private UIFactory myFactory = new BasicUIFactory();

    public Node createGraphic (ObservableList<WaveBlockDefinition> list, WaveBlockDefinition item) {
        HBox hbox = new HBox(toDouble(myBundle.getString("Buffer")));
        hbox.getChildren().add(SpriteToImage(item));
        hbox.getChildren().add(createCount(item));
        hbox.getChildren().add(createGap(item));
        hbox.getChildren().add(createDelete(list, item));
        return hbox;
    }

    public <E> Node createDelete (ObservableList<E> list, E item) {
        Button button = myFactory.createImageButton(myBundle.getString("DeleteURL"));
        button.setOnMouseClicked(e -> list.remove(item));
        return button;
    }

    private Node createGap (WaveBlockDefinition item) {
        Label title = myFactory.createSubTitleLabel(myLang.getString("SpawnGapTimeMS"));
        Label val = myFactory.createSubTitleLabel(Double.toString(item.getGap()));
        return createVBox(title, val);
    }

    private Node createVBox (Node n1, Node n2) {
        VBox vbox = new VBox(Double.parseDouble(myBundle.getString("Cushion")));
        vbox.getChildren().addAll(n1, n2);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private Node createCount (WaveBlockDefinition item) {
        Label title = myFactory.createSubTitleLabel(myLang.getString("Count"));
        Label val = myFactory.createSubTitleLabel(Integer.toString(item.getCount()));
        return createVBox(title, val);
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

    public Image getTransferImage (WaveDragCell waveDragCell) {
        return new Image(myBundle.getString("ImageURL"));
    }

}
