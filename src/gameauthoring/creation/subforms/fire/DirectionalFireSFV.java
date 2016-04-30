package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import splash.LocaleManager;
import util.StringParser;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * View representing a subform that creates the information required to build a directional mover
 * module
 * 
 * @author Dhrumil Timko
 *
 */
public class DirectionalFireSFV extends SubFormView implements IDirectionalFireSFV {

    private HBox myPane;
    private ResourceBundle myLabel;
    private String myAngleKey;
    private String myWaitTimeKey;
    private String myProjectileKey;
    private String myRangedKey;
    private String myRangeValueKey;
    private NumberEntryView myAngle;
    private NumberEntryView myWaitTime;
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private RemoveOption myRemove;
    private CheckEntryView myIsRanged;
    private NumberEntryView myRangeValue;
    private StringParser s;

    public DirectionalFireSFV (IDefinitionCollection<SpriteDefinition> missiles,
                               RemoveOption remove) {
        setResourceBundleAndKey();
        s = new StringParser();
        double width = s.parseDouble(getMyNumbers().getString("Width"));
        double height = s.parseDouble(getMyNumbers().getString("Height"));
        myRemove = remove;
        myAngle = new NumberEntryView(myAngleKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);
        myWaitTime = new NumberEntryView(myWaitTimeKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>(myProjectileKey, missiles.getItems(),
                                            AuthoringView.DEFAULT_ENTRYVIEW);

        myIsRanged =
                new CheckEntryView(myRangedKey, AuthoringView.DEFAULT_ENTRYVIEW);
        myRangeValue =
                new NumberEntryView(myRangeValueKey, width, height, AuthoringView.DEFAULT_ENTRYVIEW);

        initView();
        initBinding();
    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myAngleKey = myLabel.getString("AngleKey");
        myWaitTimeKey = myLabel.getString("WaitTimeKey");
        myProjectileKey = myLabel.getString("ProjectileKey");
        myRangedKey = myLabel.getString("RangedKey");
        myRangeValueKey = myLabel.getString("RangeValueKey");
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        double spacing = s.parseDouble(getMyNumbers().getString("HBoxSpacing"));
        myPane =
                getMyUIFactory().makeHBox(spacing, Pos.TOP_LEFT, myMissileSelectionView.draw(),
                                          myWaitTime.draw(), myAngle.draw(),
                                          myRangeValue.draw(), myIsRanged.draw(), myRemove.draw());
        myPane.getStyleClass().add("firer");
    }

    private void initBinding () {
        myRangeValue.draw().visibleProperty().bind(myIsRanged.isCheckedProperty());

    }

    @Override
    public double getMyAngle () {
        return myAngle.getData();
    }

    @Override
    public double getMyWaitTime () {
        return myWaitTime.getData();
    }

    @Override
    public SpriteDefinition getMissileSelection () {
        return myMissileSelectionView.getSelected();
    }

    @Override
    public void populateWithData (SpriteDefinition missile,
                                  double angle,
                                  double waitTime,
                                  double range,
                                  boolean isRanged) {
        myMissileSelectionView.setSelected(missile);
        myAngle.setData(angle);
        myWaitTime.setData(waitTime);
        myRangeValue.setData(range);
        myIsRanged.setSelected(isRanged);
    }

    @Override
    public double getMyRange () {
        return myRangeValue.getData();
    }

    @Override
    public boolean getMyIsRanged () {
        return myIsRanged.isCheckedProperty().get();
    }

}
