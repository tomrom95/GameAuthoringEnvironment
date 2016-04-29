package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * View representing a subform that creates the information required to build a directional mover
 * module
 * 
 * @author Dhrumil
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
    private CheckEntryView isRangedSelectionView;
    private NumberEntryView myRangeValue;

    public DirectionalFireSFV (IDefinitionCollection<SpriteDefinition> missiles,
                               RemoveOption remove) {
        setResourceBundleAndKey();
        myRemove = remove;
        myAngle = new NumberEntryView(myAngleKey, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        myWaitTime = new NumberEntryView(myWaitTimeKey, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>(myProjectileKey, missiles.getItems(),
                                            AuthoringView.DEFAULT_ENTRYVIEW);
        //
        // isRangedSelectionView =
        // new CheckEntryView(myRangedKey, AuthoringView.DEFAULT_ENTRYVIEW);
         //myRangeValue = new NumberEntryView(myRangeValueKey, this.getData(), 150, 30,
        // AuthoringView.DEFAULT_ENTRYVIEW);
        //
        initView();
        //initBinding();
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
        myPane =
                getMyUIFactory().makeHBox(20, Pos.TOP_LEFT, myMissileSelectionView.draw(),
                                          myWaitTime.draw(), myAngle.draw(), myRemove.draw());
        // TODO: add range
        myPane.getStyleClass().add("firer");
    }

    private void initBinding () {
        myRangeValue.draw().visibleProperty().bind(isRangedProperty());

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
    public void populateWithData (SpriteDefinition missile, double angle, double waitTime) {
        myMissileSelectionView.setSelected(missile);
        myAngle.setData(angle);
        myWaitTime.setData(waitTime);
    }

    @Override
    public String getMyRangedKey () {
        return myRangedKey;
    }

    @Override
    public String getMyRangeValueKey () {
        return myRangeValueKey;
    }

    @Override
    public BooleanProperty isRangedProperty () {
        return this.isRangedSelectionView.isCheckedProperty();

    }

}
