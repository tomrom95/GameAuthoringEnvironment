package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class MovementSubFormView extends SubFormView {

    private ObservableList<ISubFormView> myViews;
    private String myMoveTypeKey = "Movement Type: ";
    private String mySpeedKey = "Speed: ";
    private GridPane myPane = new GridPane();
    private StaticMoverSubFormController myStaticSubForm;
    private ConstantMoverSubFormController myConstantSubForm;
    private UserMoverSubFormController myUserSubForm;
//    private TrackingMoverSubFormController myTrackingSubForm;
    private static final int STATIC_INDEX = 0;
    private static final int CONSTANT_INDEX = 1;
    private static final int USER_INDEX = 2;
    private static final int TRACKING_INDEX = 3;
    
    private IEntryView myMovement, mySpeed;
    private List<IEntryView> myEntryViews;                                                                                     

    public MovementSubFormView () {
        setUpMovementTypes();
        initializeMovementTypeViews();
        updateEntryViews();
        initView();
    }

    private void setUpMovementTypes () {
        myStaticSubForm = new StaticMoverSubFormController();
        myConstantSubForm = new ConstantMoverSubFormController();
        myUserSubForm = new UserMoverSubFormController();
        // myTrackingSubForm = new TrackingMoverSubFormController();
    }

    private void initializeMovementTypeViews () {
        myViews.add(STATIC_INDEX, myStaticSubForm.getSubFormView());
        myViews.add(CONSTANT_INDEX, myConstantSubForm.getSubFormView());
        myViews.add(USER_INDEX, myUserSubForm.getSubFormView());
//        myViews.add(TRACKING_INDEX, myTrackingSubForm.getSubFormView());
    }
    
    private void updateEntryViews () {
        myMovement = new SingleChoiceEntryView(myMoveTypeKey, myViews, 20);
        mySpeed = new TextEntryView(mySpeedKey, this.getData(), 20, 100, 100);
        myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myMovement, mySpeed));
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.add(myMovement.draw(), 0, 0);
        myPane.add(mySpeed.draw(), 1, 0);
    }

    public String getMyMoveTypeKey () {
        return myMoveTypeKey;
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }
}
