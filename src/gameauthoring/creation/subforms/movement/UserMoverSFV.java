package gameauthoring.creation.subforms.movement;

import java.util.ResourceBundle;
import splash.LocaleManager;
import gameauthoring.creation.entryviews.CharacterEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class UserMoverSFV extends SubFormView implements IUserMoverSFV {

    private GridPane myPane = new GridPane();
    private ResourceBundle myLabel;
    private String mySpeedKey;
    private String myUpKey;
    private String myDownKey;
    private String myRightKey;
    private String myLeftKey;
    private NumberEntryView mySpeed;
    private CharacterEntryView myUp;
    private CharacterEntryView myDown;
    private CharacterEntryView myRight;
    private CharacterEntryView myLeft;

    public UserMoverSFV () {
        setResourceBundleAndKey();
        createEntryViews();
        initView();
    }

    private void createEntryViews () {
        double width = getParser().parseDouble(getMyNumbers().getString("WidthMovement"));
        double height = getParser().parseDouble(getMyNumbers().getString("Height"));
        mySpeed =
                new NumberEntryView(mySpeedKey, width, height,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myUp =
                new CharacterEntryView(myUpKey, width, height,
                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myDown =
                new CharacterEntryView(myDownKey, width, height,
                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myRight =
                new CharacterEntryView(myRightKey, width, height,
                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myLeft =
                new CharacterEntryView(myLeftKey, width, height,
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
        double spacing = getParser().parseDouble(getMyNumbers().getString("HBoxSpacing"));
        HBox box = getMyUIFactory().makeHBox(spacing, Pos.CENTER, myPane);
        getMyUIFactory().addStyling(box, "Mover");
        return box;
    }

    @Override
    protected void initView () {        
        myPane.add(mySpeed.draw(), 0, 0);
        myPane.add(myUp.draw(), 0, 1);
        myPane.add(myDown.draw(), 0, 2);
        myPane.add(myLeft.draw(), 1, 0);
        myPane.add(myRight.draw(), 1, 1);
    }

    @Override
    public double getSpeed () {
        return mySpeed.getData();
    }

    @Override
    public String getUpKey () {
        return myUp.getData();
    }

    @Override
    public String getDownKey () {
        return myDown.getData();
    }

    @Override
    public String getLeftKey () {
        return myLeft.getData();
    }

    @Override
    public String getRightKey () {
        return myRight.getData();
    }

    @Override
    public void populateWithData (double speed, String up, String down, String left, String right) {
        mySpeed.setData(speed);
        myUp.setData(up);
        myDown.setData(down);
        myLeft.setData(left);
        myRight.setData(right);

    }

}
