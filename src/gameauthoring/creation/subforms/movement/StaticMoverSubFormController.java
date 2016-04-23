package gameauthoring.creation.subforms.movement;

import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ConstantMoverDefinition;
import engine.definitions.moduledef.StaticMovementDefintion;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * static movement modules.
 * <p>
 * Creates movement modules for immovable towers.
 * 
 * @author Dhrumil
 *
 */

public class StaticMoverSubFormController implements ISubFormControllerSprite {

    private StaticMoverSubFormView myView;
    private IFormDataManager myFormData;

    public StaticMoverSubFormController () {
        myView = new StaticMoverSubFormView();
        myFormData = myView.getData();
    }

    @Override
    public void updateItem (SpriteDefinition item) {

        StaticMovementDefintion myMovementDefinition = new StaticMovementDefintion();

        item.setMovementDefinition(myMovementDefinition);

    }

    public void populateViewsWithData (SpriteDefinition item) {

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void initializeFields () {
        // TODO Auto-generated method stub
        
    }

}
