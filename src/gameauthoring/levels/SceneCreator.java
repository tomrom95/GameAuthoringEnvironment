package gameauthoring.levels;

import engine.IGame;
import engine.ILevel;
import engine.rendering.AuthoringRenderer;
import engine.rendering.GridRenderer;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


/**
 * View class for creating the scene creator, which consists
 * of the level itself and a sidebar view of created sprites to add
 * 
 * @author Tommy
 *
 */
public class SceneCreator implements Glyph {
    private final static String DEFAULT_BACKGROUND = "images/grass.jpg";
    public final static int HEIGHT = 400;
    public final static int WIDTH = 700;

    private IGame gameModel;
    private AuthoringRenderer myLevelView;
    private GridRenderer myTileView;
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

    /**
     * Makes the sprite selection side bar. Uses a list view
     * of custom listcells to visualize the sprites based on their
     * profiles.
     * 
     * @return
     */
    private Node createSpriteSelection () {
        return new AuthoringSideBar(gameModel, myLevelView).draw();
    }

    /**
     * Creates the level view using an authoring renderer to
     * keep track of the sprites on screen
     * 
     * @return
     */
    private Node createLevelView () {
//        Pane levelPane = new Pane();
        // myLevelView = new AuthoringRenderer(myLevel, levelPane);
        // myLevelView.render();
        // levelPane.setOnMouseClicked(e -> handleMouseClick(e));
        // return levelPane;
        
        myController.setBackground(DEFAULT_BACKGROUND);
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        myTileView = new GridRenderer(gridPane);
        myTileView.render();
        return gridPane;
    }

    /**
     * Handles mouse click actions for the scene
     * 
     * @param e
     */
    private void handleMouseClick (MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
            myController.uploadNewBackground();
            myLevelView.render();
        }
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {

        }
    }

}
