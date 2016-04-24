package gameauthoring.tabs;

import java.util.Locale;
import java.util.ResourceBundle;
import engine.IGame;
import gameauthoring.creation.entryviews.FormDataManager;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.entryviews.ImageEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * GameTabViewer serves to display Text/Image EntryView to authors so that they can fill in the game
 * information such as name of the game, author of the game, and an image file for splash screen.
 * 
 * TODO: Multiple Language for label
 * 
 * @author Jin An
 *
 */
public class GameTabViewer implements ITabViewer {

    private GridPane myLayout;
    private IFormDataManager myData = new FormDataManager();
    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);

    private String myNameKey = myLang.getString("NameOfGame");
    private String myAuthorKey = myLang.getString("Author");
    private String myDateCreated = myLang.getString("Date");
    private String mySplashScreenKey = myLang.getString("SplashScreen");
    private static final int HORIZONTAL_GAP = 150;

    public GameTabViewer () {
        init();
    }

    public GameTabViewer (IGame iGame) {
        init();
    }

    @Override
    public void init () {
        myLayout = new GridPane();
        createEntryViews();
    }

    private void createEntryViews () {
        IEntryView name = new TextEntryView(myNameKey, myData, 200, 60, "titleScreen");
        IEntryView author = new TextEntryView(myAuthorKey, myData, 200, 60, "titleScreen");
        IEntryView dateCreated = new TextEntryView(myDateCreated, myData, 200, 60, "titleScreen");
        IEntryView splashScreen =
                new ImageEntryView(mySplashScreenKey, myData, 600, 400,
                                   AuthoringView.DEFAULT_ENTRYVIEW);
        myLayout.add(name.draw(), 0, 0);
        myLayout.add(author.draw(), 0, 1);
        myLayout.add(dateCreated.draw(), 0, 2);
        myLayout.add(splashScreen.draw(), 1, 0, 1, 3);
        myLayout.setHgap(HORIZONTAL_GAP);
    }

    @Override
    public Node draw () {
        return myLayout;
    }
}
