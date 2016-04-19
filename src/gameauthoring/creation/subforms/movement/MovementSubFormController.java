package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class MovementSubFormController implements ISubFormControllerSprite {

    private MovementSubFormView myView;
    private ObservableList<ISubFormView> mySubFormViews;
    private IFormDataManager myFormData;
    private List<ISubFormController<SpriteDefinition>> mySubFormControllers;
    private ISubFormController<SpriteDefinition> myCurrentMovementController;
    private StaticMoverSubFormController myStaticSubForm;
    private ConstantMoverSubFormController myConstantSubForm;
    private UserMoverSubFormController myUserSubForm;
    private TrackingMoverSubFormController myTrackingSubForm;

    public MovementSubFormController () {
        setUpSubFormControllers();
        setUpSubFormViews();
        this.myView = new MovementSubFormView(mySubFormViews, e -> changeMovement(e));
        this.myFormData = myView.getData();
    }

    private void setUpSubFormControllers () {
        myStaticSubForm = new StaticMoverSubFormController();
        myConstantSubForm = new ConstantMoverSubFormController();
        myUserSubForm = new UserMoverSubFormController();
        myTrackingSubForm = new TrackingMoverSubFormController(null); //TODO: add game to constructor
        mySubFormControllers = new ArrayList<>();
        mySubFormControllers.addAll(Arrays
                .asList(myStaticSubForm, myConstantSubForm, myUserSubForm, myTrackingSubForm));
        myCurrentMovementController = mySubFormControllers.get(0);

    }

    private void setUpSubFormViews () {
        mySubFormViews = FXCollections.observableArrayList();
        mySubFormViews.add(myStaticSubForm.getSubFormView());
        mySubFormViews.add(myConstantSubForm.getSubFormView());
        mySubFormViews.add(myUserSubForm.getSubFormView());
        mySubFormViews.add(myTrackingSubForm.getSubFormView());
    }

    // combo box handler
    private void changeMovement (int comboSelectionIndex) {
        myCurrentMovementController = mySubFormControllers.get(comboSelectionIndex);
        myView.changeSubMovementView(comboSelectionIndex);
        
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        myCurrentMovementController.updateItem(item);
    }

//    @Override
//    public void populateViewsWithData (SpriteDefinition item) {
//        myCurrentMovementController.populateViewsWithData(item);
//    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void initializeFields () {
        for (ISubFormController<SpriteDefinition> subFormController: mySubFormControllers){
            subFormController.initializeFields();
        }
    }

}
