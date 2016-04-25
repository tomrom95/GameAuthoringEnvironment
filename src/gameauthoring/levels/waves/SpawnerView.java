package gameauthoring.levels.waves;

import engine.IGame;
import engine.ILevel;
import engine.definitions.spawnerdef.SpawnerDefinition;
import engine.definitions.spawnerdef.SpawnerModuleDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import engine.profile.Profile;
import engine.rendering.AuthoringRenderer;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.Coordinate;
import util.StringParser;


public class SpawnerView implements Glyph, Draggable {
    private static final String DRAG_STRING = "Spawner";

    private HBox myPane = new HBox();
    private UIFactory myFactory = new BasicUIFactory();
    private ListView<WaveDefinition> myWaves;
    private ObservableList<WaveDefinition> myBacking = FXCollections.observableArrayList();
    private SpawnerDefinition mySpawner;
    private ILevel myLevel;
    private IGame myGame;
    private TextField myDelay;
    private AuthoringRenderer myRenderer;

    public SpawnerView (IGame game, ILevel level, AuthoringRenderer renderer) {
        init();
        myLevel = level;
        myGame = game;
        myRenderer = renderer;
        myPane.getStyleClass().add("spawner_border");
    }

    private void init () {
        myWaves = new ListView<>(myBacking);
        myWaves.setPlaceholder(new Label("Drag waves here to\n associate them\n with the spawner"));
        myWaves.getStyleClass().add("list_green");
        myDelay = myFactory.createTextField("Enter gap", 100);
        myWaves.setMinWidth(150);
        myPane.getChildren().add(getLeft());
        myPane.getChildren().add(myWaves);

    }

    private Node getLeft () {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().add(getView());
        vbox.getChildren().add(myDelay);
        vbox.getChildren().add(myFactory.createButton("Clear", e -> reset()));
        return vbox;
    }

    private Node getView () {
        ImageView view = new ImageView(getImage());
        view.setOnDragDetected(e -> setOnDragDetected(e, view));
        view.setFitWidth(70);
        view.setFitHeight(70);
        return view;
    }

    private Image getImage () {
        return new Image("images/spawner.png");
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void add (WaveDefinition wave) {
        myWaves.getItems().add(wave);
    }

    public double getDelay () throws NumberFormatException {
        return new StringParser().parseDouble(myDelay.getText());
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

    private void createSpawner () {
        try {
            SpawnerModuleDefinition spawnerDef =
                    new SpawnerModuleDefinition(myLevel, myGame, getDelay(), myWaves.getItems());
            mySpawner = new SpawnerDefinition(myGame);
            mySpawner.setProfile(new Profile("", "", "images/transfer.png"));
            mySpawner.setMySpawningModule(spawnerDef);
        }
        catch (NumberFormatException e) {
            return;
        }
    }

    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.COPY);
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        myLevel.add(mySpawner.create(), new Coordinate(e.getX(), e.getY()));
        myRenderer.render();
        reset();
    }

    private void reset () {
        myBacking.clear();
        myDelay.clear();      
    }

    @Override
    public boolean checkPlaceable (DragEvent e) {
        return true;
    }

}
