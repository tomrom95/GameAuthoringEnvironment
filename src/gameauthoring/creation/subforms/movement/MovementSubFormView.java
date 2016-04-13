package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * This serves to display movement subform view. It creates a combo box which allows the author 
 * @author Jin An
 *
 */
public class MovementSubFormView extends SubFormView {

    private ObservableList<ISubFormView> myViews;
    private String myMoveTypeKey = "Movement Type: ";
    private GridPane myPane = new GridPane();
    private IEntryView myMovement;
    private List<IEntryView> myEntryViews;                                                                                     

    public MovementSubFormView (ObservableList<ISubFormView> views) {
        this.myViews = views;
        updateEntryViews();
        initView();
    }
    
    private void updateEntryViews () {
        myMovement = new SingleChoiceEntryView(myMoveTypeKey, myViews, 20);
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
