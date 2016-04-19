package gameauthoring.creation.subforms.movement;

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
 * View representing a sub form that creates the information required to build a constant mover
 * module
 * 
 * @author Dhrumil
 *
 */
public class ConstantMoverSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();

    private String mySpeedKey = "Speed: ";
    private String myOrientationKey = "Initial Orientation: ";

    private IEntryView mySpeed = new NumberEntryView(mySpeedKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myOrientation = new NumberEntryView(myOrientationKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);

    private List<IEntryView> myEntryViews =
            new ArrayList<IEntryView>(Arrays.asList(mySpeed, myOrientation));

    public ConstantMoverSubFormView () {
        initView();
    }
    
    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane.setGridLinesVisible(true);
        myPane.add(mySpeed.draw(), 0, 0);
        myPane.add(myOrientation.draw(), 0, 1);
    }

    public String getMySpeedKey () {
        return mySpeedKey;
    }

    public String getMyOrientationKey () {
        return myOrientationKey;
    }

}
