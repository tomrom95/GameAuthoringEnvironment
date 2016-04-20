package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.SpriteGroup;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * View representing a subform that creates the information required to build a tracking mover
 * module
 * 
 * @author Dhrumil
 * @author Joe Lilien
 *
 */
public class TrackingFirerSFV extends SubFormView implements ITrackingFireSFV {

    private GridPane myPane = new GridPane();
    private String myWaitTimeKey = "Wait Time: ";
    private String myTargetsKey = "Targets: ";
    private IEntryView myWaitTime;
    private SingleChoiceEntryView<SpriteGroup> myTargets;

    public TrackingFirerSFV (DefinitionCollection<SpriteGroup> groupsList) {
        myWaitTime =
                new NumberEntryView(myWaitTimeKey, this.getData(), 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myTargets =
                new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, groupsList.getItems(),
                                                       AuthoringView.DEFAULT_ENTRYVIEW);
        initView();

    }

    public SingleChoiceEntryView<SpriteGroup> getTargetsCoice () {
        return myTargets;
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane.setGridLinesVisible(true);
        myPane.add(myWaitTime.draw(), 0, 0);
        myPane.add(myTargets.draw(), 1, 0);
    }

    @Override
    public String getWaitTimeKey () {
        return myWaitTimeKey;
    }

    @Override
    public String getTargetsKey () {
        return myTargetsKey;
    }

}
