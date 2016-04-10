package gameauthoring.characters;

import java.util.List;
import engine.definitions.SpriteDefinition;

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
    public void updateGameModel (SpriteDefinition item) {
        myCurrentMovementController.updateGameModel(item);
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
