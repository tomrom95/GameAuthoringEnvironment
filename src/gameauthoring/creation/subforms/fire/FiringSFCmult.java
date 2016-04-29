package gameauthoring.creation.subforms.fire;

import java.util.*;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.DirectionalFirerDefinition;
import engine.definitions.moduledef.FirerDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import engine.definitions.moduledef.TrackingFirerDefinition;
import engine.definitions.moduledef.UserFirerDefinition;
import gameauthoring.creation.factories.DynamicSFCFactory;
import gameauthoring.creation.subforms.DynamicSubFormView;
import gameauthoring.creation.subforms.ISubFormController;
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
    private FiringSFCFactory mySFCFactory;
    private List<String> options = new ArrayList<>(Arrays.asList("TRACKING", "DIRECTIONAL"));
    private IGame myGame;

    public FiringSFCmult (IGame game) {
        myGame = game;
        mySFCFactory = new FiringSFCFactory();
        myView = new FiringSFVmult(options);
        mySFCs = new ArrayList<>();
        setActions();
    }

    private void setActions () {
        for (int i = 0; i < myView.getMyButtons().size(); i++) {
            String sfcID = options.get(i);
            myView.setButtonAction(myView.getMyButtons().get(i),
                                   e -> addSFC(mySFCFactory
                                           .createSubFormController(sfcID, getMyGame(), this)));
        }
    }

    public void addSFC (RemovableSpriteSFC sfc) {
        mySFCs.add(sfc);
        // sfc.initializeFields();
        myView.addOrSetSFV(sfc.getSubFormView());
    }

    private void clearSFCs () {
        mySFCs = new ArrayList<>();
        myView.clearSFVs();
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

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        this.clearSFCs();

        // TODO: change to list<FiringDefinition>
        List<ModuleDefinition> firingDefs = item.getModuleDefinitions();

        System.out.println(firingDefs.toString());
        for (ModuleDefinition firingDef : firingDefs) {
            RemovableSpriteSFC sfc = mySFCFactory.createSubFormController(getMyGame(), this, (FirerDefinition) firingDef);
            sfc.populateViewsWithData(item);
            this.addSFC(sfc);
        }

    }

    private IGame getMyGame () {
        return myGame;
    }

}
