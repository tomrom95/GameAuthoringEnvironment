package gameauthoring.creation.subforms.movement;

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

    private String myXVelKey = "X Velocity: ";
    private String myYVelKey = "Y Velocity: ";

    private IEntryView myXVel = new TextEntryView(myXVelKey, this.getData(), 20, 150, 30);
    private IEntryView myYVel = new TextEntryView(myYVelKey, this.getData(), 20, 150, 30);

    private List<IEntryView> myEntryViews =
            new ArrayList<IEntryView>(Arrays.asList(myXVel, myYVel));

    public TrackingFireSubFormView () {
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        myPane.add(myXVel.draw(), 0, 0);
        myPane.add(myYVel.draw(), 0, 1);
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }

}
