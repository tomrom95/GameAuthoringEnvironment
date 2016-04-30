package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import engine.AuthorshipData;
import engine.definitions.concrete.SpriteDefinition;
import splash.LocaleManager;
import util.StringParser;
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


public class UserFireSFV extends SubFormView implements IUserFireSFV {

    private HBox myPane;
    private GridPane controlPane;
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
    private StringParser s;


    public UserFireSFV (AuthorshipData data, RemoveOption remove) {
        setResourceBundleAndKey();
        myRemove = remove;
        createEntryViews(data);
        initView();
    }

    private void createEntryViews (AuthorshipData data) {
        s = new StringParser();
        double width = s.parseDouble(getMyNumbers().getString("Width"));
        double height = s.parseDouble(getMyNumbers().getString("Height"));

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
                new CharacterEntryView(myIncreaseKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);

        myDecrease =
                new CharacterEntryView(myDecreaseKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);

        myFire = new CharacterEntryView(myFireKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);

        myAngleStep = new NumberEntryView(myAngleStepKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);

        myIsRanged =
                new CheckEntryView(myRangedKey, AuthoringView.DEFAULT_ENTRYVIEW);
        myRangeValue =
                new NumberEntryView(myRangeValueKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);
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
        // return myPane;
        return controlPane;
    }

    @Override
    protected void initView () {
        controlPane.setGridLinesVisible(true);
        controlPane.add(myIncrease.draw(), 0, 0);
        controlPane.add(myDecrease.draw(), 0, 1);
        controlPane.add(myFire.draw(), 1, 0);

        myPane =
                getMyUIFactory().makeHBox(20, Pos.TOP_LEFT, myMissileSelectionView.draw(),
                                          myAngle.draw(), myAngleStep.draw(),
                                          myWaitTime.draw(),
                                          myRangeValue.draw(), myIsRanged.draw(), myRemove.draw());

        myPane.getStyleClass().add("firer");
        controlPane.add(myPane, 2, 0);

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
