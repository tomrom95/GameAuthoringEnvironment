package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.AuthorshipData;
import engine.SpriteGroup;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.UIFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * View representing a subform that creates the information required to build a tracking mover
 * module
 * 
 * @author Dhrumil
 * @author Joe Lilien
 *
 */
public class TrackingFirerSFV extends SubFormView implements ITrackingFireSFV {

    private HBox myPane;
    private String myWaitTimeKey = "Wait Time: ";
    private String myTargetsKey = "Targets: ";
    private IEntryView myWaitTime;
    private SingleChoiceEntryView<SpriteGroup> myTargets;
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private UIFactory myUIFactory= new UIFactory();
    private RemoveOption myRemove;

    public TrackingFirerSFV (AuthorshipData data, RemoveOption remove) {
        myRemove = remove;
        myWaitTime =
                new NumberEntryView(myWaitTimeKey, this.getData(), 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myTargets =
                new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, data.getMyCreatedGroups().getItems(),
                                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>("Missile", data.getMyCreatedMissiles().getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        initView();

    }
    
    @Override
    protected void initView () {
        myPane = myUIFactory.makeHBox(20, Pos.TOP_LEFT, myMissileSelectionView.draw(), myWaitTime.draw(), myTargets.draw(), myRemove.draw());
        myPane.getStyleClass().add("firer");
    }

    public SingleChoiceEntryView<SpriteGroup> getTargetsCoice () {
        return myTargets;
    }

    @Override
    public Node draw () {
        return myPane;
    }


    @Override
    public String getWaitTimeKey () {
        return myWaitTimeKey;
    }

    @Override
    public String getTargetsKey () {
        return myTargetsKey;
    }

    @Override
    public SpriteDefinition getSelectedMissile () {
        return myMissileSelectionView.getSelected();
    }

}
