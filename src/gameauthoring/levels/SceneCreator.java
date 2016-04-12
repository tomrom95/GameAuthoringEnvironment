package gameauthoring.levels;

import engine.IGame;
import engine.ILevel;
import engine.definitions.SpriteDefinition;
import engine.rendering.AuthoringRenderer;
import gameauthoring.levels.sprites.DraggableSpriteCell;
import gameauthoring.util.Glyph;
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

/**
 * View class for creating the scene creator, which consists
 * of the level itself and a sidebar view of created sprites to add
 * @author Tommy
 *
 */
public class SceneCreator implements Glyph {
    private final static String DEFAULT_BACKGROUND = "images/grass.jpg";
    public final static int HEIGHT = 400;
    public final static int WIDTH = 700;

    private IGame gameModel;
    private AuthoringRenderer levelView;
    private ILevel myLevel;
    private SceneController myController;

    public SceneCreator (IGame model, ILevel level) {
        gameModel = model;
        myLevel = level;
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

    /**
     * Makes the sprite selection side bar. Uses a list view
     * of custom listcells to visualize the sprites based on their
     * profiles. TODO - make its own class
     * @return
     */
    private Node createSpriteSelection () {
        // TODO implement grouping
        Accordion selector = new Accordion();
        ListView<SpriteDefinition> spriteList = new ListView<SpriteDefinition>();
       
        spriteList.setItems(gameModel.getAuthorshipData().getMyCreatedSprites().get(0).getItems());//0 is only enemies. change to put this in accordion view
        
        spriteList.setCellFactory(new Callback<ListView<SpriteDefinition>, ListCell<SpriteDefinition>>() {
            @Override 
            public ListCell<SpriteDefinition> call(ListView<SpriteDefinition> list) {
                return new DraggableSpriteCell(levelView, myController);
            }
        });
        
        TitledPane friendlies = new TitledPane("Sprites", spriteList);

        selector.getPanes().addAll(friendlies);
        return selector;
    }
    
    /**
     * Creates the level view using an authoring renderer to
     * keep track of the sprites on screen
     * @return
     */
    private Node createLevelView () {
        Pane levelPane = new Pane();
        
        levelView = new AuthoringRenderer(myLevel, levelPane);
        
        myController.setBackground(DEFAULT_BACKGROUND);
        levelView.render();

        levelPane.setOnMouseClicked(e -> handleMouseClick(e));
        return levelPane;
    }

    /**
     * Handles mouse click actions for the scene
     * @param e
     */
    private void handleMouseClick (MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
            myController.uploadNewBackground();
            levelView.render();
        } 
    }

}
