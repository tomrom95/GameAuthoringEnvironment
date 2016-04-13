package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * View representing a subform that creates the information required to build a tracking mover
 * module
 * 
 * @author Dhrumil
 *
 */
public class TrackingFireSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();

    private String myWaitTimeKey = "Wait Time: ";
    private String myProjectileKey = "Projectile: ";
    private String myTargetsKey = "Targets: ";

    private IEntryView myWaitTime = new TextEntryView(myWaitTimeKey, this.getData(), 20, 150, 30);
    private IEntryView myProjectile =
            new TextEntryView(myProjectileKey, this.getData(), 20, 150, 30);
    private IEntryView myTargets = new TextEntryView(myTargetsKey, this.getData(), 20, 150, 30);
    private List<IEntryView> myEntryViews =
            new ArrayList<IEntryView>(Arrays.asList(myWaitTime, myProjectile, myTargets));

    public TrackingFireSubFormView () {
        initView();
    }

    @Override
    public Node draw () {
        // TODO Auto-generated method stub
        return myPane;
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        myPane.add(myWaitTime.draw(), 0, 0);
        myPane.add(myProjectile.draw(), 0, 1);
        myPane.add(myTargets.draw(), 1, 0);
    }

    public String getWaitTimeKey () {
        return myWaitTimeKey;
    }

    public String getTargetsKey () {
        return myTargetsKey;
    }

    public String getMyProjectileKey () {
        return myProjectileKey;
    }
}
