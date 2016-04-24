package gameauthoring.creation.subforms.fire;

import java.util.*;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * Allows User to add or remove multiple firing module definitions to a sprite definition, contains
 * a list of more specific SFC's that allow for specification of actual module details
 * 
 * @author Joe Lilien
 *
 */
public class FiringSFCmult implements ISubFormControllerSprite {
    private FiringSFVmult myView;
    private List<RemovableSpriteSFC> mySFCs;

    public FiringSFCmult (IGame game) {
        myView = new FiringSFVmult(game, this);
        mySFCs = new ArrayList<>();
    }

    public void addSFC (RemovableSpriteSFC sfc) {
        mySFCs.add(sfc);
        sfc.initializeFields();
        myView.addSFV(sfc.getSubFormView());
    }

    public void removeSFC (RemovableSpriteSFC sfc) {
        mySFCs.remove(sfc);
        myView.removeSFV(sfc.getSubFormView());
        sfc.removeModule(sfc.getFirerDefinition());
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        mySFCs.forEach(e -> e.updateItem(item));
    }

    @Override
    public void initializeFields () {
        // TODO Auto-generated method stub

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
