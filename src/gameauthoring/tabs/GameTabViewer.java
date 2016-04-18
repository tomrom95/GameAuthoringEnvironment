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
import javafx.scene.layout.VBox;


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

    private GridPane myLayout;
    private IFormDataManager myData = new FormDataManager();

    private String myNameKey = "Name of the Game: ";
    private String myAuthorKey = "Author: ";
    private String mySplashScreenKey = "Splash Screen: ";
    private IEntryView myName = new TextEntryView(myNameKey, myData, 200, 60, "titleScreen");
    private IEntryView myAuthor = new TextEntryView(myAuthorKey, myData, 200, 60, "titleScreen");
    private IEntryView mySplashScreen = new ImageEntryView(mySplashScreenKey, myData, 600, 400, AuthoringView.DEFAULT_ENTRYVIEW);

    public GameTabViewer () {
        init();
    }

    public GameTabViewer (Game game) {
        init();
    }

    @Override
    public void init () {
        myLayout = new GridPane();
        myLayout.add(myName.draw(), 0, 0);
        myLayout.add(myAuthor.draw(), 0, 1);
        myLayout.add(mySplashScreen.draw(), 1, 0, 1, 2);
        myLayout.setHgap(150);
    }

    @Override
    public Node draw () {
        return myLayout;
    }
}
