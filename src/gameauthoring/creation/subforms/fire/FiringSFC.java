package gameauthoring.creation.subforms.fire;

import java.util.*;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.DirectionalFirerDefinition;
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
public class FiringSFC implements ISubFormControllerSprite {
    private FiringSFV myView;
    private List<RemovableSpriteSFC> mySFCs;
    private FiringSFCFactory mySFCFactory;
    private ResourceBundle myClassPaths = ResourceBundle.getBundle("defaults/class_path_def");

    // TODO: move to resource file
    private List<String> options = new ArrayList<>(Arrays.asList("TRACKINGFIRER", "DIRECTIONALFIRER"));

    public FiringSFC (IGame game) {
        mySFCFactory = new FiringSFCFactory(game, this);
        myView = new FiringSFV(options);
        mySFCs = new ArrayList<>();
        setActions();
    }

    private void setActions () {
        for (int i = 0; i < myView.getMyButtons().size(); i++) {
            String sfcID = options.get(i);
            myView.setButtonAction(myView.getMyButtons().get(i),
                                   e -> addSFC(mySFCFactory.createSubFormController(myClassPaths.getString(sfcID))));
        }
    }

    public void addSFC (RemovableSpriteSFC sfc) {
        mySFCs.add(sfc);
        myView.addOrSetSFV(sfc.getSubFormView());
    }

    private void clearSFCs () {
        mySFCs.clear();
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
        for (ModuleDefinition firingDef : firingDefs) {
            RemovableSpriteSFC sfc =
                    mySFCFactory.createSubFormController(firingDef.getClass().getName(),
                                                         firingDef);
            sfc.populateViewsWithData(item);
            this.addSFC(sfc);

        }

    }

}
