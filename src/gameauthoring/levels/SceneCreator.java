package gameauthoring.levels;

import engine.IGame;
import engine.ILevel;
import engine.rendering.AuthoringRenderer;
import engine.rendering.GridRenderer;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


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
    private ILevel myLevel;
    private SceneController myController;
    private boolean myPlaceableEnable;

    public SceneCreator (IGame model, ILevel level) {
        gameModel = model;
        myLevel = level;
        myController = new SceneController(myLevel);
        myPlaceableEnable = false;
    }

    @Override
    public Node draw () {
        HBox container = new HBox(10);
        container.getChildren().add(createLevelView());
        container.getChildren().add(createSpriteSelection());
        return container;
    }

    private void handlePlaceableButton (Pane pane, Pane levelPane, Pane gridPane) {
        myPlaceableEnable = !myPlaceableEnable;
        pane.getChildren().clear();
        if (myPlaceableEnable)
            pane.getChildren().addAll(levelPane, gridPane,
                                      placeableButton(pane, levelPane, gridPane));
        else
            pane.getChildren().addAll(gridPane, levelPane,
                                      placeableButton(pane, levelPane, gridPane));

    }

    private Button placeableButton (Pane pane, Pane levelPane, Pane gridPane) {
        return (new UIFactory())
                .createButton((myPlaceableEnable ? "Disable" : "Enable") + " Placeable View",
                              e -> handlePlaceableButton(pane, levelPane, gridPane));
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

        Pane root = new StackPane();
        Pane levelPane = new Pane();
        GridPane gridPane = new GridPane();
        Button enablePlaceableButton = placeableButton(root, levelPane, gridPane);

        gridPane.setGridLinesVisible(true);
        root.getChildren().addAll(gridPane, levelPane, enablePlaceableButton);
        myController.setBackground(DEFAULT_BACKGROUND);
        myLevelView = new AuthoringRenderer(myLevel, levelPane, gridPane);
        myLevelView.render();
        levelPane.setOnMouseClicked(e -> handleMouseClick(e));
        return root;
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
    }
}
