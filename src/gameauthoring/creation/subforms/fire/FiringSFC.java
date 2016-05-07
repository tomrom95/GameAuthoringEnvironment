// This entire file is part of my masterpiece.
// Joseph Lilien

/**
 * This class was included as a part of my masterpiece to serve as an example of a subclass that
 * implements MultiSFController, which is the main focus of the masterpiece. This class takes great
 * advantage of all of the resources and structures provided by its superclass via inheritance and
 * completes the implementation to allow for control over the creation and management of one or more
 * FiringModuleDefinitions that can be added to a SpriteDefinition by the User. As mentioned before,
 * it is very open for extension in that it can support the additional creation of any number of new
 * FiringModuleDefinition types (like Tracking, UserDefined, etc.) with just the creation of the two
 * additional classes (SFV and RemovableSFC for the new type) and the addition of one line in the
 * associated properties files. The MultiOptionFactory should handle the rest.
 * 
 */
package gameauthoring.creation.subforms.fire;

import java.util.List;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.IMultiSFView;
import gameauthoring.creation.subforms.dynamic.MultiSFController;
import util.BundleOperations;


/**
 * Allows User to add or remove multiple firing module definitions to a sprite definition, contains
 * a list of more specific SFC's that allow for specification of actual module details
 * 
 * @author Joseph Lilien
 */

public class FiringSFC extends MultiSFController<SpriteDefinition> {

    private static final String FIRING_KEY = "Firing";

    /**
     * This class serves to manage and control one or more type specific firing SFCs (i.e.
     * TrackingFireSFC) t allow the user to define, edit or remove any number of
     * FiringModuleDefinitions to the SpriteDefinition that is being created
     * 
     * @param view
     * @param game
     */
    public FiringSFC (IMultiSFView view, IGame game) {
        super(view, game);
        setMyOptions(BundleOperations.getPropertyValueAsList(FIRING_KEY, getMyOptionsFile()));
        setMyView(view);
        setViewActions();
    }

    @Override
    protected List<? extends Object> getListofEditables (SpriteDefinition item) {
        return item.getModuleDefinitions();
    }

    @Override
    protected void resetContents (SpriteDefinition item) {
        item.getModuleDefinitions().clear();
    }

    @Override
    public void initializeFields () {
        // Nothing to initialize for default case 
    }

}
