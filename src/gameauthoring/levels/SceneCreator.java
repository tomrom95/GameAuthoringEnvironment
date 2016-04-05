package gameauthoring.levels;


import engine.IGame;
import gameauthoring.Glyph;
import gameauthoring.ListCellView;
import gameauthoring.SpriteCellView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class SceneCreator implements Glyph {
    private final static String DEFAULT_BACKGROUND = "images/blank.jpg";
    public final static int HEIGHT = 400;
    public final static int WIDTH = 700;

    private IGame gameModel;
    private Pane myLevel;
    private SceneController myController;
    private DoubleProperty myHeight;

    public SceneCreator (IGame model) {
        gameModel = model;
        myHeight = new SimpleDoubleProperty(HEIGHT);
        myController = new SceneController();
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
        selector.maxHeightProperty().bind(myHeight);
        ListView<Node> testList = new ListView<Node>();

        for (int i = 0; i < 12; i++) {
            SpriteCellView sprite = new DraggableSpriteCell(null, myLevel, myController);
            Node spriteNode = sprite.draw();
            testList.getItems().add(spriteNode);
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

    private Node createLevelView () {
        myLevel = new Pane();
        myController.setBackground(myLevel, DEFAULT_BACKGROUND);

        myLevel.setOnMouseClicked(e -> handleMouseClick(e, myLevel));
        return myLevel;
    }

    private void handleMouseClick (MouseEvent e, Pane container) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
            myController.uploadNewBackground(e, container);
            e.consume();
        } 
    }

}
