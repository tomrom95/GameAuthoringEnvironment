package gameauthoring.creation.subforms.movement;

import java.util.List;
import engine.definitions.MovementDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.StaticMoverDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

public class MovementSubFormController implements ISubFormControllerSprite {

    private MovementSubFormView myView;
    private IFormDataManager myFormData;
    private List<ISubFormController<SpriteDefinition>> mySubFormControllers;
    private ISubFormController<SpriteDefinition> myCurrentMovementController;

    public MovementSubFormController() {
        this.myView = new MovementSubFormView();
        this.myFormData = myView.getData();
    }
    
    //combo box handler
    private void changeMovement(int comboSelectionIndex) {
        myCurrentMovementController = mySubFormControllers.get(comboSelectionIndex);
    }
    
    @Override
    public void updateItem (SpriteDefinition item) {
        myCurrentMovementController.updateItem(item);
        item.setMovementDefinition(new StaticMoverDefinition());
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
