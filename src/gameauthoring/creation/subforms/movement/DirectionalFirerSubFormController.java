package gameauthoring.creation.subforms.movement;

import java.util.function.Predicate;
import engine.IGame;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.ModuleDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * linear, directional movement modules
 * 
 * @author Dhrumil Timko
 *
 */

public class DirectionalFirerSubFormController implements ISubFormControllerSprite {

    private DirectionalFirerSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    
    
    private static Predicate<ModuleDefinition> findDirectionalFirer() {
        return p -> p.getClass().equals(new DirectionalFirerDefinition().getClass());
    }
    public DirectionalFirerSubFormController (IGame game) {
        myView = new DirectionalFirerSubFormView();
        myFormData = myView.getData();
        myGame = game;
        
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        Object firingDefinitionObject =
                 item.getModuleDefinitions().stream().filter(findDirectionalFirer()).toArray()[0];
        DirectionalFirerDefinition myDef = new DirectionalFirerDefinition();
        if(firingDefinitionObject.getClass().equals(myDef.getClass())){
            myDef = (DirectionalFirerDefinition) firingDefinitionObject;
        } else{
            myDef = null;
            /*
             * throw exception here?
             */
        }

        // myFormData.set(myView.getMyXVelKey(),
        // Double.toString(movementDefinition.getXVel()));
        // myFormData.set(myView.getMyYVelKey(),
        // Double.toString(movementDefinition.getYVel()));

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        
    }

}
