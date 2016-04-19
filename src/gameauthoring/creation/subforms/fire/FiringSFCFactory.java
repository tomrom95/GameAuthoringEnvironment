package gameauthoring.creation.subforms.fire;

import engine.IGame;
import gameauthoring.creation.subforms.DynamicSFCFactory;
import gameauthoring.creation.subforms.ISubFormControllerSprite;


public class FiringSFCFactory extends DynamicSFCFactory {

    private FiringSubFormController myFiringSFC;

    public FiringSFCFactory (IGame game, FiringSubFormController firingSFC) {

        super(game);
        myFiringSFC = firingSFC;
    }

    public ISubFormControllerSprite createSpriteSubFormController (String type) {
        if (type.equals("DirectionalFire")) {
            return new DirectionalFireSubFormController(getMyGame(), getMyFiringSFC());
        }
        else if (type.equals("TrackingFire")) {
            return new TrackingFireSubFormController(getMyGame(), getMyFiringSFC());
        }
        return null;
    }

    private FiringSubFormController getMyFiringSFC () {
        return myFiringSFC;
    }
}
