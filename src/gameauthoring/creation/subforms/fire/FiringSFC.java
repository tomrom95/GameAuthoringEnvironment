package gameauthoring.creation.subforms.fire;

import java.util.List;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.dynamic.MultiOptionSFC;
import util.BundleOperations;


/**
 * Allows User to add or remove multiple firing module definitions to a sprite definition, contains
 * a list of more specific SFC's that allow for specification of actual module details
 *
 * @author Joe Lilien
 *
 */

public class FiringSFC extends MultiOptionSFC<SpriteDefinition> {

    private static final String FIRING_KEY = "Firing";

    public FiringSFC (IGame game) {
        super(game);
        // setMySFCFactory(new FiringSFCFactory(game));
        setMyOptions(BundleOperations.getPropertyValueAsList(FIRING_KEY, getMyOptionsFile()));
        setMyView(new FiringSFV(getMyOptions(), FIRING_KEY));
        setActions();
    }

    @Override
    protected List<? extends Object> getList (SpriteDefinition item) {
        return item.getModuleDefinitions();
    }

    @Override
    protected void resetContents (SpriteDefinition item) {
        item.getModuleDefinitions().clear();
    }

}
