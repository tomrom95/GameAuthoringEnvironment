package gameauthoring.gameinformation;

import java.util.Locale;
import java.util.ResourceBundle;
import splash.LocaleManager;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import gameauthoring.creation.entryviews.FormDataManager;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.entryviews.ImageEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.Glyph;


/**
 * View class for Game Information. It creates entryViews for name, author, data created, and
 * splashscreen of the game that allow authors to put information.
 * 
 * @author Jin An
 *
 */
public class GameInfoView implements Glyph {

    private GridPane myLayout;
    private IFormDataManager myData = new FormDataManager();
    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private String myNameKey = myLang.getString("NameOfGame");
    private String myAuthorKey = myLang.getString("Author");
    private String myDateCreatedKey = myLang.getString("Date");
    private String mySplashScreenKey = myLang.getString("GameIcon");
    private IEntryView myName;
    private IEntryView myAuthor;
    private IEntryView myDateCreated;
    private IEntryView mySplashScreen;
    private static final int HORIZONTAL_GAP = 150;

    public GameInfoView () {
        createEntryViews();
    }
    
    @Override
    public Node draw () {
        myLayout = new GridPane();
        initView();
        return myLayout;
    }

    private void createEntryViews () {
        myName = new TextEntryView(myNameKey, myData, 200, 60, "titleScreen");
        myAuthor = new TextEntryView(myAuthorKey, myData, 200, 60, "titleScreen");
        myDateCreated = new TextEntryView(myDateCreatedKey, myData, 200, 60, "titleScreen");
        mySplashScreen = new ImageEntryView(mySplashScreenKey, myData, 200, 300,
                                            AuthoringView.DEFAULT_ENTRYVIEW);
    }

    private void initView () {
        myLayout.add(myName.draw(), 0, 0);
        myLayout.add(myAuthor.draw(), 0, 1);
        myLayout.add(myDateCreated.draw(), 0, 2);
        myLayout.add(mySplashScreen.draw(), 1, 0, 1, 3);
        myLayout.setHgap(HORIZONTAL_GAP);
    }

    public IFormDataManager getData () {
        return myData;
    }

    public String getMyNameKey () {
        return myNameKey;
    }

    public String getMyAuthorKey () {
        return myAuthorKey;
    }

    public String getMyDateCreatedKey () {
        return myDateCreatedKey;
    }

    public String getMySplashScreenKey () {
        return mySplashScreenKey;
    }
}
