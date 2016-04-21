package gameauthoring.levels;

import java.io.File;
import engine.IGame;
import engine.ILevel;
import gameauthoring.creation.entryviews.FormDataManager;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.entryviews.IListCellView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.listdisplay.LevelConditionView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.Glyph;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;


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
    private IFormDataManager myData = new FormDataManager();
    private IGame myGame;
    private ILevel myLevel;
    private String myNumberEnemyKey = "Number of Enemies: ";
    private String myRateKey = "Rate: ";
    private String myWinConditionKey = "Win Condition: ";
    private String myLoseConditionKey = "Lose Condition: ";
    // TODO
//    private IEntryView myNumberEnemy = new TextEntryView(myNumberEnemyKey, myData, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
//    private IEntryView myRate = new TextEntryView(myRateKey, myData, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
//    private IEntryView myWinCondition = new TextEntryView(myWinConditionKey, myData, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
//    private IEntryView myLoseCondition = new TextEntryView(myLoseConditionKey, myData, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);

    LevelConditionView myConditions; 
    
    public LevelEditorView (IGame gameModel, ILevel level) {
        myGame = gameModel;
        myLevel = level;
        myConditions = new LevelConditionView(myGame, myLevel);
    }

    @Override
    public Node draw () {
        myLayout = new BorderPane();
        myLayout.setCenter((new SceneCreator(myGame, myLevel)).draw());
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
        // TODO 
        return myConditions.draw();
    }

    private Node createWavesForm () {
        // TODO
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER_LEFT);
        form.setMinHeight(300);
        form.setMinWidth(200);
        //form.add(myWinCondition.draw(), 0, 1);
        //form.add(myLoseCondition.draw(), 0, 2);
        return form;
    }
}
