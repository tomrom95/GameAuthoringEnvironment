package gameauthoring;

import gameauthoring.characters.FormDataManager;
import gameauthoring.characters.IEntryView;
import gameauthoring.characters.IFormDataManager;
import gameauthoring.characters.ImageEntryView;
import gameauthoring.characters.TextEntryView;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Handles selection between different games
 * 
 * @author Jin An
 *
 */
public class GameTabViewer implements ITabViewer {

    private BorderPane myLayout;
    private IFormDataManager myData = new FormDataManager();
    
    private String myNameKey = "Name of the Game: ";
    private String myAuthorKey = "Author: ";
    private String mySplashScreenKey = "Splash Screen: ";
    
    private IEntryView myName = new TextEntryView(myNameKey, myData, 20, 150, 30);
    private IEntryView myAuthor = new TextEntryView(myAuthorKey, myData, 20, 150, 30);
    private IEntryView mySplashScreen = new ImageEntryView(mySplashScreenKey, myData, 20, 150, 30);

    public GameTabViewer(){
        init();
    }
    
    public void init(){
        myLayout = new BorderPane();
        myLayout.setPrefSize(1200, 800);
        myLayout.setCenter(createForms());        
    }
    
    @Override
    public Node draw () {
        return myLayout;
    }

    private Node createForms(){
        HBox box = new HBox(800);
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().add(createGameInfoForm());
        return box;
    }
    
    private Node createGameInfoForm(){
        GridPane form = new GridPane();
        form.add(myName.draw(), 0, 0);
        form.add(myAuthor.draw(), 0, 1);
        form.add(mySplashScreen.draw(), 0, 2);
        form.setMinSize(200, 200);

//        form.add(loadImage, 1, 3);

        return form;
    }
    
    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

}
