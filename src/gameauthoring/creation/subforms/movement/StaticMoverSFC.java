package gameauthoring.creation.subforms.movement;

import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.StaticMovementDefintion;
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

public class StaticMoverSFC implements ISubFormControllerSprite {

    private StaticMoverSFV myView;

    public StaticMoverSFC () {
        myView = new StaticMoverSFV();
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        
        StaticMovementDefintion myMovementDefinition = new StaticMovementDefintion();
        item.setMovementDefinition(myMovementDefinition);

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void initializeFields (SpriteDefinition item) {
        // TODO Auto-generated method stub
        
    }

}
