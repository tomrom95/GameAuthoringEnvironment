package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * View representing a subform that creates the information required to build a directional mover
 * module
 * 
 * @author Dhrumil
 *
 */
public class DirectionalFireSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();

    private String myAngleKey = "Angle: ";
    private String myWaitTimeKey = "Wait Time: ";
    private String myProjectileKey = "Projectile: ";

    private IEntryView myAngle =
            new NumberEntryView(myAngleKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myWaitTime =
            new NumberEntryView(myWaitTimeKey, this.getData(), 150, 30,
                              AuthoringView.DEFAULT_ENTRYVIEW);  

    private List<IEntryView> myEntryViews =
            // new ArrayList<IEntryView>(Arrays.asList(myAngle, myWaitTime, myProjectile));
            new ArrayList<IEntryView>(Arrays.asList(myAngle, myWaitTime));

    public DirectionalFireSubFormView () {
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        myPane.add(myAngle.draw(), 0, 0);
        myPane.add(myWaitTime.draw(), 0, 1);
        // myPane.add(myProjectile.draw(), 1, 0);
    }

    public String getMyProjectileKey () {
        return myProjectileKey;
    }

    public String getMyAngleKey () {
        return myAngleKey;
    }

    public String getMyWaitTimeKey () {
        return myWaitTimeKey;
    }

}
