package gameauthoring.levels;

import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;
import gameauthoring.levels.waves.SpawnerAuthoringView;
import gameauthoring.util.Glyph;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import util.ScaleRatio;


/**
 * View wrapper for visualizing the level as you create it.
 * Will include a side panel for adding sprites and another
 * one for defining waves.
 * 
 * Scales the view of the game as appropriate to fit the screen. Most components of the screen are
 * statically sized, to allow for easy reading. The view of the level screen then fits
 * the remaining space.
 * 
 * @author Tommy
 * @author Jin An
 * @author RyanStPierre
 *
 */
public class LevelEditorView implements Glyph {

    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/level_editor_view");
    private BorderPane myLayout;
    private IGame myGame;
    private ILevel myLevel;
    private SceneCreator myScene;
    private SpawnerAuthoringView mySpawningView;
    private AuthoringLevelConditions myLevelConditions;
    private ScaleRatio myScale = new ScaleRatio();

    public LevelEditorView (IGame gameModel, ILevel level) {
        myGame = gameModel;
        myLevel = level;
        myLevelConditions = new AuthoringLevelConditions(gameModel, level);
    }

    @Override
    public Node draw () {
        myLayout = new BorderPane();
        myScene = new SceneCreator(myGame, myLevel, myScale);
        mySpawningView = new SpawnerAuthoringView(myGame, myLevel, myScene.getRenderer(), myScale);
        myLayout.setRight(mySpawningView.draw());
        myLayout.setCenter(myScene.draw());
        myLayout.setBottom(createBottomForms());
        return myLayout;
    }

    private Node createBottomForms () {
        HBox box = new HBox(Double.parseDouble(myBundle.getString("Cushion")));
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().add(createWinLoseForm());

        return box;
    }

    private Node createWinLoseForm () {
        return myLevelConditions.draw();
    }

    public void rescale (double width, double height) {
        double xScale = getXScale(width);
        double yScale = getYScale(height);
        myScale.setScale(Math.min(xScale, yScale));
        myScene.render();
    }

    private double getYScale (double height) {
        double remainingHeight =
                height - myLevelConditions.getHeight() -
                                 Double.parseDouble(myBundle.getString("Offset"));
        double levelBound = myLevel.getBounds().getHeight();
        return remainingHeight / levelBound;
    }

    private double getXScale (double width) {

        double remainingWidth = width - mySpawningView.getWidth() - myScene.getAccordionWith();
        double levelBound = myLevel.getBounds().getWidth();
        return remainingWidth / levelBound;
    }

}
