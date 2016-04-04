package gameauthoring.levels;

import java.io.File;
import engine.IGame;
import gameauthoring.Glyph;
import gameauthoring.ListCellView;
import gameauthoring.SpriteCellView;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;


public class SceneCreator implements Glyph {
    private final static String DEFAULT_BACKGROUND = "images/pvz.jpg";
    private final static int HEIGHT = 400;
    private final static int WIDTH = 700;

    private IGame gameModel;
    private PathCreator pathCreator;
    private SpriteDragDrop dragDrop;

    public SceneCreator (IGame model) {
        gameModel = model;
        pathCreator = new PathCreator();
    }

    @Override
    public Node draw () {
        HBox container = new HBox(10);
        container.getChildren().add(createLevelView());
        container.getChildren().add(createSpriteSelection());
        return container;
    }

    @Override
    public void update () {

    }

    private Node createSpriteSelection () {
        Accordion selector = new Accordion();
        selector.setMaxHeight(HEIGHT);
        ListView<Node> testList = new ListView<Node>();

        for (int i = 0; i < 12; i++) {
            SpriteCellView sprite = new SpriteCellView(null);
            Node spriteNode = sprite.draw();
            testList.getItems().add(spriteNode);
            addDraggableEvent(sprite, spriteNode);
        }
        TitledPane enemies = new TitledPane("Enemies", testList);
        testList = new ListView<Node>();
        for (int i = 0; i < 9; i++) {
            ListCellView sprite = new SpriteCellView(null);
            testList.getItems().add(sprite.draw());
        }
        TitledPane friendlies = new TitledPane("Defenders", testList);
        selector.getPanes().addAll(enemies, friendlies);
        return selector;
    }

    private void addDraggableEvent (SpriteCellView cell, Node spriteNode) {
        spriteNode.setOnDragDetected(e -> {
            dragDrop.newDrag(cell, spriteNode);
        });
    }

    private Node createLevelView () {
        Pane container = new Pane();
        setBackground(container, DEFAULT_BACKGROUND);

        container.setOnMouseClicked(e -> handleMouseClick(e, container));
        container.setOnKeyPressed(e -> handleKeyPress(e, container));
        dragDrop = new SpriteDragDrop(container);
        return container;
    }

    private void handleKeyPress (KeyEvent e, Pane container) {
        if (e.getCode() == KeyCode.ESCAPE) {
            pathCreator.endPath(container);
        }
    }

    private void handleMouseClick (MouseEvent e, Pane container) {
        if (e.getButton() == MouseButton.SECONDARY) {
            // uploadNewBackground(e, container);
        }
        else if (pathCreator.isCreatingPath()) {
            pathCreator.addToPath(e, container);
        }
        else {
            pathCreator.newPath(e, container);
        }
    }

    private void uploadNewBackground (MouseEvent e, Pane container) {
        if (e.getButton() != MouseButton.SECONDARY) {
            return;
        }
        File newImage = (new FileChooser()).showOpenDialog(null);
        if (newImage == null)
            return;
        setBackground(container, newImage.toURI().toString());
    }

    public void setBackground (Region container, String imagePath) {
        Image img = new Image(imagePath, WIDTH, HEIGHT, true, true);
        BackgroundImage background = new BackgroundImage(
                                                         img,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundPosition.DEFAULT,
                                                         BackgroundSize.DEFAULT);
        container.setBackground(new Background(background));
        container.setMinWidth(img.getWidth());
        container.setMinHeight(HEIGHT);
    }

}
