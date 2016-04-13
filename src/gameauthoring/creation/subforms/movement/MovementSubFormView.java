package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Node mySubMovementView;

    public MovementSubFormView (ObservableList<ISubFormView> views, Consumer<Integer> action) {
        this.myViews = views;
        updateEntryViews(action);
        initView();
    }

    private void updateEntryViews (Consumer<Integer> action) {
        myListOfMovementTypes = FXCollections.observableArrayList();
        ProfileDisplay staticMove = new ProfileDisplay("static");
        ProfileDisplay constantMove = new ProfileDisplay("constant");
        ProfileDisplay userMove = new ProfileDisplay("User Defined");
        ProfileDisplay trackingMove = new ProfileDisplay("Tracking");
        
        myListOfMovementTypes.addAll(staticMove, constantMove, userMove, trackingMove);
        SingleChoiceEntryView<ProfileDisplay> entryView =  new SingleChoiceEntryView<ProfileDisplay>(myMoveTypeKey, myListOfMovementTypes, 20);
        entryView.addComboListener(action);
        mySubMovementView = myViews.get(0).draw();
        myPane.add(mySubMovementView, 1, 0);
        entryView.setSelected(staticMove);
        myMovement = entryView;
        myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myMovement));
    }
    
    public void changeSubMovementView(int index){
        myPane.getChildren().remove(mySubMovementView);
        mySubMovementView = myViews.get(index).draw();
        myPane.add(mySubMovementView, 1, 0);
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
