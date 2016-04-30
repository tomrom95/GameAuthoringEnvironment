package gameauthoring.levels;

import util.ScaleRatio;
import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;
import engine.rendering.AuthoringRenderer;
import gameauthoring.util.Glyph;
import gameauthoring.util.BasicUIFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
 * @author Jin An
 *
 */
public class SceneCreator implements Glyph {
    
    private static final String DEFAULT_BACKGROUND = "images/grass.jpg";
    private static final int DOUBLE_CLICK = 2;
    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/scene_creator");
    private IGame myGameModel;
    private AuthoringRenderer myRenderer;
    private Node myView;
    private ILevel myLevel;
    private SceneController myController;
    private boolean myPlaceableEnable;
    private ScaleRatio myScale;
    private AuthoringSideBar mySideBar;
    private GridPane myGrid;
    private Button myButton;

    public SceneCreator (IGame model, ILevel level, ScaleRatio scale) {
        myGameModel = model;
        myLevel = level;
        myScale = scale;
        myController = new SceneController(myLevel, scale);
        myPlaceableEnable = false;
        myView = createContainer();
    }

    private Node createContainer () {
        HBox container = new HBox(Double.parseDouble(myBundle.getString("Cushion")));
        container.getChildren().add(createLevelView());
        container.getChildren().add(createSpriteSelection());

        return container;
    }

    @Override
    public Node draw () {
        return myView;
    }

    private Button placeableButton () {
        return (new BasicUIFactory())
                .createButton((myPlaceableEnable ? "Disable" : "Enable") + " Placeable View",
                              e -> handlePlaceableButton());
    }

    private void handlePlaceableButton () {

        myPlaceableEnable = !myPlaceableEnable;
        if (myPlaceableEnable) {
            myGrid.setDisable(false);
            myGrid.setVisible(true);
        }
        else {
            disableGrid();
        }
        myButton.setText((myPlaceableEnable ? "Disable" : "Enable") + " Placeable View");
    }

    /**
     * Makes the sprite selection side bar. Uses a list view
     * of custom listcells to visualize the sprites based on their
     * profiles.
     * 
     * @return
     */
    private Node createSpriteSelection () {
        mySideBar = new AuthoringSideBar(myGameModel, myLevel, myRenderer, myScale);
        return mySideBar.draw();
    }

    /**
     * Creates the level view using an authoring renderer to
     * keep track of the sprites on screen
     * 
     * @return
     */
    private Node createLevelView () {

        
        Pane pane = new Pane();
        Pane levelPane = new Pane();
        myGrid = new GridPane();
        myButton = placeableButton();
        myController.setBackground(DEFAULT_BACKGROUND);
        disableGrid();
        myLevel.setBackgroundImageSize(Double.parseDouble(myBundle.getString("Width")),
                                       Double.parseDouble(myBundle.getString("Height")));
        myRenderer = new AuthoringRenderer(myLevel, levelPane, myGrid, myScale);
        myRenderer.render();
        levelPane.setOnMouseClicked(e -> handleMouseClick(e));
        pane.getChildren().addAll(levelPane, myGrid, myButton);
        return pane;
    }

    public void disableGrid () {
        myGrid.setDisable(true);
        myGrid.setVisible(false);
    }

    /**
     * Handles mouse click actions for the scene
     * 
     * @param e
     */
    private void handleMouseClick (MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == DOUBLE_CLICK) {
            myController.uploadNewBackground();
            myRenderer.updateNewTiles();
            updateBitMap();
            render();
        }
    }

    private void updateBitMap () {
        myLevel.initializePlaceableTiles(myRenderer.getGrids().getNumBlockRow(), myRenderer
                .getGrids()
                .getNumBlockCol());
    }

    public AuthoringRenderer getRenderer () {
        return myRenderer;
    }

    public void render () {
        myRenderer.render();
        myRenderer.redrawBackground();
        myRenderer.scaleTitles();

    }

    public double getAccordionWith () {
        return mySideBar.getWidth();
    }
}
