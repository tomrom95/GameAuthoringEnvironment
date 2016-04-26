package gameauthoring.creation.factories;

import engine.IGame;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormController;


/**
 * This is an abstract factory class for creating sprite subformcontrollers
 * for a dynamic subformcontroller. Essentially, this is creating the sub-subformcontrollers
 * that the dynamic subformcontroller chooses between
 * 
 * @author Jeremy Schreck
 *
 */
public abstract class DynamicSFCFactory<T extends IProfilable> {

    private IGame myGame;

    /**
     * Constructor
     * 
     * @param game The current game object
     */
    public DynamicSFCFactory (IGame game) {
        myGame = game;
    }

    /**
     * Method for creating a sprite SFC based on a string ID for that SFC
     * 
     * @param type A string indicating which SFC to instantiate
     * @return The ISubFormControllerSprite that the factory creates
     */
    public abstract ISubFormController<T> createSubFormController (String type);
    
    public abstract ISubFormController<T> createSubFormController (String type, Object ... params );


    // Getters and Setters
    protected IGame getMyGame () {
        return myGame;
    }
}
