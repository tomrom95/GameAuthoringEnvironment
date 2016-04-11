package gameauthoring.levels;

import java.io.File;
import engine.IGame;
import engine.ILevel;
import gameauthoring.Glyph;
import gameauthoring.SpriteCellView;
import gameauthoring.characters.FormDataManager;
import gameauthoring.characters.IEntryView;
import gameauthoring.characters.IFormDataManager;
//import gameauthoring.TextFormData;
import gameauthoring.characters.IListCellView;
import gameauthoring.characters.TextEntryView;
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
    
    private IEntryView myNumberEnemy = new TextEntryView(myNumberEnemyKey, myData, 20, 150, 30);
    private IEntryView myRate = new TextEntryView(myRateKey, myData, 20, 150, 30);
    private IEntryView myWinCondition = new TextEntryView(myWinConditionKey, myData, 20, 150, 30);
    private IEntryView myLoseCondition = new TextEntryView(myLoseConditionKey, myData, 20, 150, 30);
    
    public LevelEditorView (IGame gameModel, ILevel level) {
        myGame = gameModel;
        myLevel = level;
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
        box.getChildren().add(createWavesForm());
        box.getChildren().add(createWinLoseForm());

        return box;
    }

    private Node createWinLoseForm () {
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER_RIGHT);
        form.setMinHeight(300);
        form.setMinWidth(200);
        form.add(myNumberEnemy.draw(), 0, 1);
        form.add(myRate.draw(), 0, 2);
        return form;
    }

    private Node createWavesForm () {
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER_LEFT);
        form.setMinHeight(300);
        form.setMinWidth(200);
        form.add(myWinCondition.draw(), 0, 1);
        form.add(myLoseCondition.draw(), 0, 2);
        return form;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

}
