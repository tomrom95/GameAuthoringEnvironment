package gameauthoring.levels;

import util.Tile;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 * View class for creating the scene creator, which consists
 * of the level itself and a sidebar view of created sprites to add
 * 
 * @author Tommy
 * @author Jin An
 *
 */
public class SceneCreator implements Glyph {
    private final static String DEFAULT_BACKGROUND = "images/grass.jpg";
    public final static int HEIGHT = 400;
    public final static int WIDTH = 700;

    private IGame gameModel;
    private AuthoringRenderer myView;
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

    private void handlePlaceableButton (Pane pane) {
        myPlaceableEnable = !myPlaceableEnable;

        pane.getChildren().clear();
        if (myPlaceableEnable)
            pane.getChildren().addAll(myView.getPane(), myView.getGrids().getPane(),
                                      placeableButton(pane));
        else {
            pane.getChildren().addAll(myView.getGrids().getPane(), myView.getPane(),
                                      placeableButton(pane));
            updatePlaceableArea();
        }
    }

    /**
     * Updates the bit map which represents placeable area every time author clicks the
     * enable/disable placeable View button.
     * 
     * @param gridPane
     */
    private void updatePlaceableArea () {
        Tile[][] blocks = myView.getGrids().getBlocks();
        for (int row = 0; row < myView.getGrids().NUM_BLOCK_ROW; row++) {
            for (int col = 0; col < myView.getGrids().NUM_BLOCK_COL; col++) {
                updateCorrespondingBlock(row, col, blocks[row][col].getTile().getFill());
            }
        }
    }

    private void updateCorrespondingBlock (int row, int col, Paint color) {
        int blockSize = myView.getGrids().BLOCK_SIZE;
        for (int r = (blockSize) * row; r < (blockSize) * (row + 1); r++) {
            for (int c = (blockSize) * (col); c < (blockSize) * (col + 1); c++) {
                myLevel.getPlaceableManager().getPlaceableArea().set(r, c, color == Color.RED);
            }
        }
    }

    private Button placeableButton (Pane pane) {
        return (new BasicUIFactory())
                .createButton((myPlaceableEnable ? "Disable" : "Enable") + " Placeable View",
                              e -> handlePlaceableButton(pane));
    }

    /**
     * Makes the sprite selection side bar. Uses a list view
     * of custom listcells to visualize the sprites based on their
     * profiles.
     * 
     * @return
     */
    private Node createSpriteSelection () {
        return new AuthoringSideBar(gameModel, myView).draw();
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
        Button enablePlaceableButton = placeableButton(root);

        gridPane.setGridLinesVisible(true);
        root.getChildren().addAll(gridPane, levelPane, enablePlaceableButton);
        myController.setBackground(DEFAULT_BACKGROUND);
        myView = new AuthoringRenderer(myLevel, levelPane, gridPane);
        myView.render();
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
            myView.render();
        }
    }
}
