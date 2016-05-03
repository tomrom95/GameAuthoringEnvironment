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
public abstract class DynamicSFCFactory<T extends IProfilable> extends SubFormControllerFactory<T> {

    /**
     * Constructor
     *
     * @param game The current game object
     */
    public DynamicSFCFactory (IGame game) {
        super(game);
    }

    @Override
    public abstract ISubFormController<T> createSubFormController (String type, Object ... params);

}
