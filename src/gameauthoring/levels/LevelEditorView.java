package gameauthoring.levels;

import java.io.File;
import engine.IGame;
import gameauthoring.Glyph;
import gameauthoring.ListCellView;
import gameauthoring.SpriteCellView;
import gameauthoring.TextFormData;
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
 * @author Tommy
 * @author Jin An
 *
 */
public class LevelEditorView implements Glyph {

    private BorderPane myLayout;
    private LevelEditorController myLevelController;
    
    public LevelEditorView (IGame gameModel) {
        myLevelController = new LevelEditorController(gameModel);
    }
    
    @Override
    public Node draw () {
        myLayout = new BorderPane();
        myLayout.setCenter((new SceneCreator(null)).draw());
        //myLayout.setLeft(createWaveSelection());
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
        form.add((new TextFormData("Number of Enemies: ")).draw(), 0, 1);
        form.add((new TextFormData("Rate: ")).draw(), 0, 2);
        return form;
    }

    private Node createWavesForm () {
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER_LEFT);
        form.setMinHeight(300);
        form.setMinWidth(200);
        form.add((new TextFormData("Win Condition: ")).draw(), 0, 1);
        form.add((new TextFormData("Loss Condition: ")).draw(), 0, 2);
        form.add((new TextFormData("Loss Condition: ")).draw(), 0, 2);
        return form;
    }

    private Node createSpriteSelection () {
        Accordion selector = new Accordion();
        ListView<Node> testList = new ListView<Node>();
        for(int i = 0; i < 5; i ++){
            ListCellView sprite = new SpriteCellView(null);
            testList.getItems().add(sprite.draw());
        }
        TitledPane friendlies = new TitledPane("Enemies", testList);
        selector.getPanes().add(friendlies);
        return selector;
    }

    private Node createLevelView () {
        Region container = new Region();
        
        Image img = new Image("images/pvz.jpg");
        BackgroundImage background = new BackgroundImage(
                 img,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundPosition.DEFAULT,
                 BackgroundSize.DEFAULT);
        container.setBackground(new Background(background));
        container.setMinWidth(img.getWidth());
        container.setMinHeight(img.getHeight());
        //container.heightProperty().bind(myLayout.heightProperty());
        container.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(e.getButton() == MouseButton.SECONDARY){
                uploadNewBackground(container);
            }
        });
        return container;
    }

    private void uploadNewBackground (Region container) {
        File newImage = (new FileChooser()).showOpenDialog(null);
        if (newImage == null) return;
        Image img = new Image(newImage.toURI().toString(), 0, 300, true, true);
        BackgroundImage background = new BackgroundImage(img,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundPosition.DEFAULT,
                                                         BackgroundSize.DEFAULT);
        container.setBackground(new Background(background));
        container.setMinWidth(img.getWidth());
        container.setMinHeight(img.getHeight());
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }
    
}
