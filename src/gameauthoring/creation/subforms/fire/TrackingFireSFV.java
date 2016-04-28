package gameauthoring.creation.subforms.fire;

import engine.AuthorshipData;
import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * View representing a subform that creates the information required to build a tracking mover
 * module
 * 
 * @author Dhrumil
 * @author Joe Lilien
 *
 */
public class TrackingFireSFV extends SubFormView implements ITrackingFireSFV {

    private HBox myPane;
    private String myWaitTimeKey = "Wait Time: ";
    private String myTargetsKey = "Targets: ";
    private String myMissileKey = "Missile: ";
    private NumberEntryView myWaitTime;
    private SingleChoiceEntryView<SpriteGroup> myTargets;
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private RemoveOption myRemove;

    public TrackingFireSFV (AuthorshipData data, RemoveOption remove) {
        myRemove = remove;
        myWaitTime =
                new NumberEntryView(myWaitTimeKey, 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myTargets =
                new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, data.getMyCreatedGroups().getItems(),
                                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>(myMissileKey, data.getMyCreatedMissiles().getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        initView();

    }
    
    @Override
    protected void initView () {
        myPane = getMyUIFactory().makeHBox(20, Pos.TOP_LEFT, myMissileSelectionView.draw(), myWaitTime.draw(), myTargets.draw(), myRemove.draw());
        myPane.getStyleClass().add("firer");
    }

    @Override
    public SpriteGroup getTargetsCoice () {
        return myTargets.getSelected();
    }

    @Override
    public Node draw () {
        return myPane;
    }


    @Override
    public double getWaitTime () {
        return myWaitTime.getData();
    }

    @Override
    public SpriteDefinition getSelectedMissile () {
        return myMissileSelectionView.getSelected();
    }

    @Override
    public void populateWithData (SpriteDefinition missile, SpriteGroup target, double waitTime) {
        myMissileSelectionView.setSelected(missile);
        myTargets.setSelected(target);
        myWaitTime.setData(waitTime);
    }

}
