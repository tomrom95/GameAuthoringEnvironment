package gameauthoring.creation.subforms.fire;

import engine.IGame;
import gameauthoring.creation.subforms.DynamicSFCFactory;
import gameauthoring.creation.subforms.ISubFormControllerSprite;

/**
 * 
 * This is a factory class that creates different types of firing sub-subforms,
 * which the FiringSFC will dynamically switch between based on user input
 * 
 * @author Jeremy Schreck
 *
 */
public class FiringSFCFactory extends DynamicSFCFactory {

    private FiringSFC myFiringSFC;

    /**
     * Constructor 
     * 
     * @param game The current game object
     * @param firingSFC A reference to the Firing SFC that controls the sub-firing SFCs
     */
    public FiringSFCFactory (IGame game, FiringSFC firingSFC) {

        super(game);
        myFiringSFC = firingSFC;
    }

    @Override
    public ISubFormControllerSprite createSpriteSubFormController (String type) {
        if (type.equals("DirectionalFire")) {
            return new DirectionalFireSFC(getMyGame(), getMyFiringSFC());
        }
        else if (type.equals("TrackingFire")) {
            return new TrackingFireSFC(getMyGame(), getMyFiringSFC());
        }
        return null;
    }

    private FiringSFC getMyFiringSFC () {
        return myFiringSFC;
    }
}
