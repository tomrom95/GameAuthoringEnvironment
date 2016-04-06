package gameauthoring.levels;

import engine.IGame;
import engine.ILevel;
import engine.sprite.ISprite;
import gameauthoring.Glyph;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;


public class SceneCreator implements Glyph {
    private final static String DEFAULT_BACKGROUND = "images/blank.jpg";
    public final static int HEIGHT = 400;
    public final static int WIDTH = 700;

    private IGame gameModel;
    private LevelRenderer levelView;
    private ILevel myLevel;
    private SceneController myController;
    private DoubleProperty myHeight;

    public SceneCreator (IGame model, ILevel level) {
        gameModel = model;
        myLevel = level;
        myHeight = new SimpleDoubleProperty(HEIGHT);
        myController = new SceneController(myLevel);
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
        ListView<ObjectProperty<ISprite>> spriteList = new ListView<ObjectProperty<ISprite>>();
       
        spriteList.setItems(gameModel.getAuthorshipData().getCreatedSprites());
        
        spriteList.setCellFactory(new Callback<ListView<ObjectProperty<ISprite>>, ListCell<ObjectProperty<ISprite>>>() {
            @Override 
            public ListCell<ObjectProperty<ISprite>> call(ListView<ObjectProperty<ISprite>> list) {
                return new DraggableSpriteCell(levelView, myController);
            }
        });
        
        TitledPane friendlies = new TitledPane("Sprites", spriteList);

        selector.getPanes().addAll(friendlies);
        return selector;
    }
    
    private Node createLevelView () {
        Pane levelPane = new Pane();
        levelView = new LevelRenderer(myLevel, levelPane);
        levelView.render();
        
        levelView.setBackground(DEFAULT_BACKGROUND);

        levelPane.setOnMouseClicked(e -> handleMouseClick(e));
        return levelPane;
    }

    private void handleMouseClick (MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
            myController.uploadNewBackground();
            e.consume();
        } 
    }

}
