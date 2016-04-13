package gameauthoring.tabs;

import engine.Game;
import gameauthoring.creation.entryviews.FormDataManager;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.entryviews.ImageEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * GameTabViewer serves to display Text/Image EntryView to authors so that they can fill in the game
 * information such as name of the game, author of the game, and an image file for splash screen.
 * 
 * TODO: ResourceBundle for unprotected string value
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
    private String defaultTextEntry = "defaultTextEntry";
    private IEntryView myName = new TextEntryView(myNameKey, myData, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myAuthor = new TextEntryView(myAuthorKey, myData, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView mySplashScreen = new ImageEntryView(mySplashScreenKey, myData, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);

    public GameTabViewer () {
        init();
    }

    public GameTabViewer (Game game) {
        init();
    }

    @Override
    public void init () {
        myLayout = new BorderPane();
        myLayout.setPrefSize(1200, 800);
        myLayout.setCenter(createForms());
    }

    @Override
    public Node draw () {
        return myLayout;
    }

    private Node createForms () {
        HBox box = new HBox(800);
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().add(createGameInfoForm());
        return box;
    }

    private Node createGameInfoForm () {
        GridPane form = new GridPane();
        form.add(myName.draw(), 0, 0);
        form.add(myAuthor.draw(), 0, 1);
        form.add(mySplashScreen.draw(), 0, 2);
        form.setMinSize(200, 200);

        return form;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }

}
