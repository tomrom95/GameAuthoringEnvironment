package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.AuthorshipData;
import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.BasicUIFactory;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * View representing a subform that creates the information required to build a tracking mover
 * module
 * 
 * @author Dhrumil
 * @author Joe Lilien
 * @author josephtimko1
 *
 */
public class TrackingFireSFV extends SubFormView implements ITrackingFireSFV {

    private VBox myPane;
    private ResourceBundle myLabel;
    private String myWaitTimeKey;
    private String myTargetsKey;
    private String myRangedKey;
    private String myRangeValueKey;
    private String myMissileKey;
    private NumberEntryView myWaitTime;
    private SingleChoiceEntryView<SpriteGroup> myTargets;
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private RemoveOption myRemove;
    private CheckEntryView myIsRanged;
    private NumberEntryView myRangeValue;

    public TrackingFireSFV (AuthorshipData data, RemoveOption remove) {
        setResourceBundleAndKey();
        myRemove = remove;
        myWaitTime =
                new NumberEntryView(myWaitTimeKey, 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myTargets =
                new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, data.getMyCreatedGroups()
                        .getItems(),
                                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>(myMissileKey, data.getMyCreatedMissiles().getItems(),
                                            AuthoringView.DEFAULT_ENTRYVIEW);
        myIsRanged =
                new CheckEntryView(myRangedKey, AuthoringView.DEFAULT_ENTRYVIEW);
        myRangeValue =
                new NumberEntryView(myRangeValueKey, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);

        initView();
        initBinding();

    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myWaitTimeKey = myLabel.getString("WaitTimeKey");
        myTargetsKey = myLabel.getString("TargetsKey");
        myRangedKey = myLabel.getString("RangedKey");
        myRangeValueKey = myLabel.getString("RangeValueKey");
        myMissileKey = myLabel.getString("MissileKey");
    }

    @Override
    protected void initView () {
        HBox box = new HBox(myRemove.draw(),myMissileSelectionView.draw(),myWaitTime.draw(),myTargets.draw());
        HBox boxTwo =  new HBox(myIsRanged.draw(),myRangeValue.draw());
        myPane =
                getMyUIFactory().makeVBox(20, Pos.CENTER, box, boxTwo);
        getMyUIFactory().addStyling(myPane, "Firer");
    }

    @Override
    public SpriteGroup getTargetsCoice () {
        return myTargets.getSelected();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public double getMyWaitTime () {
        return myWaitTime.getData();
    }

    @Override
    public SpriteDefinition getSelectedMissile () {
        return myMissileSelectionView.getSelected();
    }

    @Override
    public void populateWithData (SpriteDefinition missile,
                                  SpriteGroup target,
                                  double waitTime,
                                  double range,
                                  boolean isRanged) {
        myMissileSelectionView.setSelected(missile);
        myTargets.setSelected(target);
        myWaitTime.setData(waitTime);
        myRangeValue.setData(range);
        myIsRanged.setSelected(isRanged);
    }

    public void setTargetsChoice (SpriteGroup targets) {
        this.myTargets.setSelected(targets);
    }

    @Override
    public double getMyRange () {
        return myRangeValue.getData();
    }

    @Override
    public boolean getMyIsRanged () {
        return myIsRanged.isCheckedProperty().get();
    }

    private void initBinding () {
        myRangeValue.draw().visibleProperty().bind(myIsRanged.isCheckedProperty());

    }

}
