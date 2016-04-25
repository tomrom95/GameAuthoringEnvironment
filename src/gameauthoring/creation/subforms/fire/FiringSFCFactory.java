package gameauthoring.creation.subforms.fire;

import engine.IGame;
import gameauthoring.creation.factories.DynamicSFCFactory;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;

/**
 * 
 * This is a factory class that creates different types of firing sub-subforms,
 * which the FiringSFC will dynamically switch between based on user input
 * 
 * @author Jeremy Schreck
 * @author Joe Lilien
 *
 */
public class FiringSFCFactory extends DynamicSFCFactory<SpriteDefinition> {

    private FiringSFCmult myFiringSFC;

    /**
     * Constructor 
     * 
     * @param game The current game object
     * @param firingSFC A reference to the Firing SFC that controls the sub-firing SFCs
     */
    public FiringSFCFactory (IGame game, FiringSFCmult myFiringSFC) {
        super(game);
        this.myFiringSFC = myFiringSFC;
    }

    @Override
    public RemovableSpriteSFC createSubFormController (String type) {
        if (type.equals("DIRECTIONAL")) {
            return new DirectionalFireSFC(getMyGame(), getMyFiringSFC());
        }
        else if (type.equals("TRACKING")) {
            return new TrackingFireSFC(getMyGame(), getMyFiringSFC());
        }
        return null;
    }

    private FiringSFCmult getMyFiringSFC () {
        return myFiringSFC;
    }
}
