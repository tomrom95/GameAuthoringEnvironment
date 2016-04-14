package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

/**
 * This is the sfc for selecting firing modules for a Sprite
 * 
 * @author Jeremy Schreck
 *
 */
public class FiringSubFormController implements ISubFormControllerSprite {

    private FiringSubFormView myView;
    private ObservableList<ISubFormView> mySubFormViews;
    private List<ISubFormController<SpriteDefinition>> mySubFormControllers;
    private ISubFormController<SpriteDefinition> myCurrentMovementController;

    public FiringSubFormController () {
        setUpSubFormControllers();
        setUpSubFormViews(mySubFormControllers);
        this.myView = new FiringSubFormView(mySubFormViews, e -> changeMovement(e));
    }

    private void setUpSubFormControllers () {
        // TOOD: add to factory
        // TODO: add game to constructor
        DirectionalFireSubFormController dfSFC = new DirectionalFireSubFormController(null);
        TrackingFireSubFormController tfSFC = new TrackingFireSubFormController(null);
        mySubFormControllers = new ArrayList<>();
        mySubFormControllers.addAll(Arrays
                .asList(dfSFC, tfSFC));
        myCurrentMovementController = mySubFormControllers.get(0);

    }

    private void setUpSubFormViews (List<ISubFormController<SpriteDefinition>> subFormControllers) {
        mySubFormViews = FXCollections.observableArrayList();
        for (ISubFormController<SpriteDefinition> sfc : subFormControllers) {
            mySubFormViews.add(sfc.getSubFormView());
        }

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

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myCurrentMovementController.populateViewsWithData(item);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
