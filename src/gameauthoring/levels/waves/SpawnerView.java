package gameauthoring.levels.waves;

import engine.definitions.spawnerdef.WaveDefinition;
import engine.waves.Wave;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SpawnerView implements Glyph {

    private BorderPane myPane = new BorderPane();
    private UIFactory myFactory = new BasicUIFactory();
    private ListView<Wave> myWaves;
    private ObservableList<Wave> myBacking = FXCollections.observableArrayList();
    
    public SpawnerView () {
        init();
    }
    
    private void init () {
        myWaves = new ListView<>(myBacking);
        myWaves.setMaxWidth(300);
        myPane.setRight(myWaves);
        myPane.setLeft(getLeft());
    }

    private Node getLeft () {
        VBox vbox = new VBox();
        vbox.getChildren().add(getView());
        vbox.getChildren().add(myFactory.createButton("Clear", e -> myBacking.clear()));
        return vbox;
    }
    
    private Node getView () {
        Image image = new Image("images/transfer.png");
        ImageView view = new ImageView(image);
        return view;
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void setOnDragOver (EventHandler<DragEvent> event) {
        myPane.setOnDragOver(event);
        
    }

    public void setOnDragDropped (EventHandler<DragEvent> event) {
        myPane.setOnDragDropped(event);
        
    }

    public void add (WaveDefinition wave) {
        myWaves.getItems().add(wave.create());
    }

}
