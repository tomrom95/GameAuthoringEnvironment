package gameauthoring.creation.subforms.fire;

import java.util.*;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.MultiOptionSFC;
import util.BundleOperations;


/**
 * Allows User to add or remove multiple firing module definitions to a sprite definition, contains
 * a list of more specific SFC's that allow for specification of actual module details
 * 
 * @author Joe Lilien
 *
 */
public class FiringSFC extends MultiOptionSFC<SpriteDefinition> {

    private String firingKey = "FIRING";

    public FiringSFC (IGame game) {
        setMySFCFactory(new FiringSFCFactory(game, this));
        setMyOptions(BundleOperations.getPropertyValueAsList(firingKey, getMyOptionsFile()));
        setMyView(new FiringSFV(getMyOptions()));
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
