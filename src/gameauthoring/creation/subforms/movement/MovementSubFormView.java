package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * This serves to display movement subform view. It creates a combo box which allows the author
 * 
 * @author Jin An
 *
 */
public class MovementSubFormView extends SubFormView {

    private ObservableList<ISubFormView> myViews;
    private String myMoveTypeKey = "Movement Type: ";
    private ObservableList<ProfileDisplay> myListOfMovementTypes;
    private GridPane myPane = new GridPane();
    private IEntryView myMovement;
    private List<IEntryView> myEntryViews;

    public MovementSubFormView (ObservableList<ISubFormView> views) {
        this.myViews = views;
        updateEntryViews();
        initView();
    }

    private void updateEntryViews () {
        myListOfMovementTypes = FXCollections.observableArrayList();
        ProfileDisplay staticMove = new ProfileDisplay("static");
        ProfileDisplay constantMove = new ProfileDisplay("constant");
        ProfileDisplay userMove = new ProfileDisplay("User Defined");
        ProfileDisplay trackingMove = new ProfileDisplay("Tracking");
        
        myListOfMovementTypes.addAll(staticMove, constantMove, userMove, trackingMove);
        myMovement = new SingleChoiceEntryView<ProfileDisplay>(myMoveTypeKey, myListOfMovementTypes, 20);
        myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myMovement));
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.add(myMovement.draw(), 0, 0);
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
