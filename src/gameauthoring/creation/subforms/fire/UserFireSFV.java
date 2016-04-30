package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import engine.AuthorshipData;
import engine.definitions.concrete.SpriteDefinition;
import splash.LocaleManager;
import gameauthoring.creation.entryviews.CharacterEntryView;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class UserFireSFV extends SubFormView implements IUserFireSFV {

    private VBox myPane;
    private ResourceBundle myLabel;
    private String myAngleKey;
    private String myWaitTimeKey;
    private String myIncreaseKey;
    private String myDecreaseKey;
    private String myFireKey;
    private String myAngleStepKey;
    private String myRangedKey;
    private String myRangeValueKey;
    private String myProjectileKey;
    private NumberEntryView myAngle;
    private NumberEntryView myWaitTime;
    private CharacterEntryView myIncrease;
    private CharacterEntryView myDecrease;
    private CharacterEntryView myFire;
    private NumberEntryView myAngleStep;
    private CheckEntryView myIsRanged;
    private NumberEntryView myRangeValue;
    private RemoveOption myRemove;
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private static final String MY_TITLE_KEY = "USERFIRER";


    public UserFireSFV (AuthorshipData data, RemoveOption remove) {
        setMyTitle(MY_TITLE_KEY);
        setResourceBundleAndKey();
        myRemove = remove;
        createEntryViews(data);
        initView();
    }

    private void createEntryViews (AuthorshipData data) {

        double width = getParser().parseDouble(getMyNumbers().getString("Width"));
        double height = getParser().parseDouble(getMyNumbers().getString("Height"));
        myMissileSelectionView =
                new SingleChoiceEntryView<>(myProjectileKey, data.getMyCreatedMissiles().getItems(),
                                            AuthoringView.DEFAULT_ENTRYVIEW);
        myAngle =
                new NumberEntryView(myAngleKey, width, height,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myWaitTime =
                new NumberEntryView(myWaitTimeKey, width, height,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myIncrease =
                new CharacterEntryView(myIncreaseKey, width, height,
                                       AuthoringView.DEFAULT_ENTRYVIEW);

        myDecrease =
                new CharacterEntryView(myDecreaseKey, width, height,
                                       AuthoringView.DEFAULT_ENTRYVIEW);

        myFire = new CharacterEntryView(myFireKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);

        myAngleStep =
                new NumberEntryView(myAngleStepKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);

        myIsRanged =
                new CheckEntryView(myRangedKey, AuthoringView.DEFAULT_ENTRYVIEW);
        myRangeValue =
                new NumberEntryView(myRangeValueKey, width, height,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        initBinding();

    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myAngleKey = myLabel.getString("AngleKey");
        myWaitTimeKey = myLabel.getString("WaitTimeKey");
        myIncreaseKey = myLabel.getString("IncreaseKey");
        myDecreaseKey = myLabel.getString("DecreaseKey");
        myFireKey = myLabel.getString("FireKey");
        myAngleStepKey = myLabel.getString("AngleStepKey");
        myRangedKey = myLabel.getString("RangedKey");
        myRangeValueKey = myLabel.getString("RangeValueKey");
        myProjectileKey = myLabel.getString("ProjectileKey");
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        double spacing = getParser().parseDouble(getMyNumbers().getString("HBoxSpacing"));
        HBox header = getMyUIFactory().makeHBox(spacing, Pos.CENTER, myRemove.draw(), getSubTitleDisplay());
        HBox angles = getMyUIFactory().makeHBox(spacing, Pos.CENTER, myDecrease.draw(), myIncrease.draw(), myFire.draw());
        HBox fireParams =
                getMyUIFactory().makeHBox(spacing, Pos.CENTER, myMissileSelectionView.draw(),
                                          myAngle.draw(), myAngleStep.draw());
        HBox rangeParams =
                getMyUIFactory().makeHBox(spacing, Pos.CENTER, myWaitTime.draw(),
                                          myIsRanged.draw(), myRangeValue.draw());
        myPane = getMyUIFactory().makeVBox(spacing, Pos.CENTER, header,angles,fireParams,rangeParams);
        getMyUIFactory().addStyling(myPane, "Firer");
    }

    @Override
    public double getMyRange () {
        return myRangeValue.getData();
    }

    @Override
    public boolean getMyIsRanged () {
        return myIsRanged.isCheckedProperty().get();
    }

    @Override
    public double getMyWaitTime () {
        return myWaitTime.getData();
    }

    private void initBinding () {
        myRangeValue.draw().visibleProperty().bind(myIsRanged.isCheckedProperty());

    }

    @Override
    public String getMyIncrease () {
        return myIncrease.getData();
    }

    @Override
    public String getMyDecrease () {
        return myDecrease.getData();
    }

    @Override
    public String getMyFire () {
        return myFire.getData();
    }

    @Override
    public double getMyAngle () {
        return myAngle.getData();
    }

    @Override
    public double getMyAngleStep () {
        return myAngleStep.getData();
    }

    @Override
    public void populateWithData (SpriteDefinition missile,
                                  double waitTime,
                                  double range,
                                  boolean isRanged,
                                  double angle,
                                  double anglestep,
                                  String increase,
                                  String decrease,
                                  String fire) {
        myMissileSelectionView.setSelected(missile);
        myWaitTime.setData(waitTime);
        myRangeValue.setData(range);
        myIsRanged.setSelected(isRanged);
        myAngle.setData(angle);
        myAngleStep.setData(anglestep);
        myIncrease.setData(increase);
        myDecrease.setData(decrease);
        myFire.setData(fire);

    }

    @Override
    public SpriteDefinition getMissileSelection () {
        return myMissileSelectionView.getSelected();
    }

}
