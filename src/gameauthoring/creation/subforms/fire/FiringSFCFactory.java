package gameauthoring.creation.subforms.fire;

import engine.IGame;
import gameauthoring.creation.factories.DynamicSFCFactory;
import gameauthoring.creation.factories.Reflection;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.DirectionalFirerDefinition;
import engine.definitions.moduledef.FirerDefinition;
import engine.definitions.moduledef.TrackingFirerDefinition;
import engine.definitions.moduledef.UserFirerDefinition;
import gameauthoring.creation.subforms.ISubFormController;
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

    private FiringSFC myFiringSFC;

    /**
     * Constructor 
     * 
     * @param game The current game object
     * @param firingSFC A reference to the Firing SFC that controls the sub-firing SFCs
     */
    public FiringSFCFactory (IGame game, FiringSFC myFiringSFC) {
        super(game);
        this.myFiringSFC = myFiringSFC;
    }

    @Override
    public RemovableSpriteSFC createSubFormController (String type) {
        if (type.equals(DirectionalFirerDefinition.class.getName())) {
            return new DirectionalFireSFC(getMyGame(), getMyFiringSFC(), new DirectionalFirerDefinition(getMyGame()));
        }
        else if (type.equals(TrackingFirerDefinition.class.getName())) {
            return new TrackingFireSFC(getMyGame(), getMyFiringSFC(), new TrackingFirerDefinition(getMyGame()));
        }
        else if (type.equals(UserFirerDefinition.class.getName())) {
            return new UserFireSFC(getMyGame(), getMyFiringSFC(), new UserFirerDefinition(getMyGame()));
        }
        return null;
    }

    @Override
    public RemovableSpriteSFC createSubFormController (String type, Object ... params) {
        
        if (type.equals(DirectionalFirerDefinition.class.getName())) {
            return new DirectionalFireSFC(getMyGame(), getMyFiringSFC(), (DirectionalFirerDefinition) params[0]);
        }
        else if (type.equals(TrackingFirerDefinition.class.getName())) {
            return new TrackingFireSFC(getMyGame(), getMyFiringSFC(),  (TrackingFirerDefinition) params[0]);
        }
        else if (type.equals(UserFirerDefinition.class.getName())) {
            return new UserFireSFC(getMyGame(), getMyFiringSFC(), (UserFirerDefinition) params[0]);
        }
        return null;
    }
    
    private FiringSFC getMyFiringSFC () {
        return myFiringSFC;
    }

    
    
}
