package gameauthoring.levels;

import engine.IGame;
import engine.ILevel;
import gameauthoring.levels.waves.SpawnerAuthoringView;
import gameauthoring.util.Glyph;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


/**
 * View wrapper for visualizing the level as you create it.
 * Will include a side panel for adding sprites and another
 * one for defining waves.
 * 
 * @author Tommy
 * @author Jin An
 *
 */
public class LevelEditorView implements Glyph {

    private BorderPane myLayout;
    private IGame myGame;
    private ILevel myLevel;

    private AuthoringLevelConditions myLevelConditions;

    public LevelEditorView (IGame gameModel, ILevel level) {
        myGame = gameModel;
        myLevel = level;
        myLevelConditions = new AuthoringLevelConditions(gameModel, level);
    }

    @Override
    public Node draw () {
        myLayout = new BorderPane();
        SceneCreator scene = new SceneCreator(myGame, myLevel);
        myLayout.setRight(new SpawnerAuthoringView(myGame, myLevel, scene.getRenderer()).draw());
        myLayout.setCenter(scene.draw());
        myLayout.setBottom(createBottomForms());
        return myLayout;
    }

    private Node createBottomForms () {
        HBox box = new HBox(10);
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().add(createWinLoseForm());

        return box;
    }

    private Node createWinLoseForm () {
        return myLevelConditions.draw();
    }
}
