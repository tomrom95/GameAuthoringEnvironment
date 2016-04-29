package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import splash.LocaleManager;
import gameauthoring.creation.entryviews.CharacterEntryView;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class UserMoverSFV extends SubFormView {

    private GridPane myPane = new GridPane();
    private ResourceBundle myLabel;
    private String mySpeedKey;
    private String myUpKey;
    private String myDownKey;
    private String myRightKey;
    private String myLeftKey;
    private IEntryView mySpeed;
    private IEntryView myUp;
    private IEntryView myDown;
    private IEntryView myRight;
    private IEntryView myLeft;
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(mySpeed,
                                                                                    myLeft,
                                                                                    myRight, myUp,
                                                                                    myDown));

    public UserMoverSFV () {
        setResourceBundleAndKey();
        createEntryViews();
        initView();
    }

    private void createEntryViews () {
        mySpeed =
                new NumberEntryView(mySpeedKey, this.getData(), 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myUp =
                new CharacterEntryView(myUpKey, this.getData(), 150, 30,
                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myDown =
                new CharacterEntryView(myDownKey, this.getData(), 150, 30,
                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myRight =
                new CharacterEntryView(myRightKey, this.getData(), 150, 30,
                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myLeft =
                new CharacterEntryView(myLeftKey, this.getData(), 150, 30,
                                       AuthoringView.DEFAULT_ENTRYVIEW);

    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        mySpeedKey = myLabel.getString("SpeedKey");
        myUpKey = myLabel.getString("UpKey");
        myDownKey = myLabel.getString("DownKey");
        myRightKey = myLabel.getString("RightKey");
        myLeftKey = myLabel.getString("LeftKey");
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane.setGridLinesVisible(true);
        myPane.add(mySpeed.draw(), 0, 0);
        myPane.add(myUp.draw(), 0, 1);
        myPane.add(myDown.draw(), 0, 2);
        myPane.add(myLeft.draw(), 1, 0);
        myPane.add(myRight.draw(), 1, 1);
    }

    public String getSpeedKey () {
        return mySpeedKey;
    }

    public String getUpKey () {
        return myUpKey;
    }

    public String getDownKey () {
        return myDownKey;
    }

    public String getLeftKey () {
        return myLeftKey;
    }

    public String getRightKey () {
        return myRightKey;
    }

}
