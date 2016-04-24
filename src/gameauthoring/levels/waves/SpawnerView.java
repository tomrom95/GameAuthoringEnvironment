package gameauthoring.levels.waves;

import engine.IGame;
import engine.ILevel;
import engine.definitions.spawnerdef.SpawnerDefinition;
import engine.definitions.spawnerdef.SpawnerModuleDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import engine.rendering.AuthoringRenderer;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import gameauthoring.levels.sprites.Draggable;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SpawnerView implements Glyph, Draggable {
    private static final String DRAG_STRING = "Spawner";

    private BorderPane myPane = new BorderPane();
    private UIFactory myFactory = new BasicUIFactory();
    private ListView<WaveDefinition> myWaves;
    private ObservableList<WaveDefinition> myBacking = FXCollections.observableArrayList();
    private SpawnerDefinition mySpawner;
    private ILevel myLevel;
    private IGame myGame;
    private AuthoringRenderer myRenderer;
    
    public SpawnerView (IGame game, ILevel level, AuthoringRenderer renderer) {
        init();
        myLevel = level;
        myGame = game;
        myRenderer = renderer;
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
        ImageView view = new ImageView(getImage());
        return view;
    }
    
    private Image getImage () {
        return new Image("images/transfer.png");
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void add (WaveDefinition wave) {
        myWaves.getItems().add(wave);
    }
    
    public double getDelay () {
        return 0;
    }

    @Override
    public void setOnDragDetected (MouseEvent e, Node node) {
        createSpawner();
        Dragboard db = node.startDragAndDrop(TransferMode.COPY);
        db.setContent(this.createClipboard(DRAG_STRING));

        db.setDragView(getImage());
       
        myRenderer.getPane().setOnDragOver(event -> setOnDragOver(event));
        myRenderer.getPane().setOnDragDropped(event -> setOnDragDropped(event));
    }
    
    private void createSpawner() {
        SpawnerModuleDefinition spawnerDef = new SpawnerModuleDefinition(myLevel, myGame, getDelay(), myWaves.getItems());
        mySpawner = new SpawnerDefinition(myGame);
        mySpawner.setMySpawningModule(spawnerDef);
    }

    @Override
    public void setOnDragOver (DragEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        myLevel.add(mySpawner.create());
    }

    @Override
    public boolean checkPlaceable (DragEvent e) {
        return true;
    }

}
