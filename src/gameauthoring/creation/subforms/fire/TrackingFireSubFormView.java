package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.SpriteGroup;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.shareddata.IDefinitionCollection;
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
public class TrackingFireSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();
    private String myWaitTimeKey = "Wait Time: ";
    private String myTargetsKey = "Targets: ";
    private IEntryView myWaitTime =
            new TextEntryView(myWaitTimeKey, this.getData(), 150, 30,
                              AuthoringView.DEFAULT_ENTRYVIEW);
    private SingleChoiceEntryView<SpriteGroup> myTargets;
    private List<IEntryView> myEntryViews =
            new ArrayList<IEntryView>(Arrays.asList(myWaitTime, myTargets));

    public TrackingFireSubFormView (DefinitionCollection<SpriteGroup> groupsList) {
        myTargets = new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, groupsList.getItems(), AuthoringView.DEFAULT_ENTRYVIEW);;
        initView();

    }    
    
    public SingleChoiceEntryView<SpriteGroup> getTargetsCoice(){
        return myTargets;
    }
    @Override
    public Node draw () {
        return myPane;
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        myPane.add(myWaitTime.draw(), 0, 0);
        myPane.add(myTargets.draw(), 1, 0);
    }

    public String getWaitTimeKey () {
        return myWaitTimeKey;
    }

    public String getTargetsKey () {
        return myTargetsKey;
    }

}
