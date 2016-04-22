package gameauthoring.creation.subforms.fire;

import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * tracking movement modules and projectiles
 * 
 * @author Dhrumil Timko Schreck
 *
 */

public class TrackingFireSFC implements ISubFormControllerSprite {

    private TrackingFirerSFV myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private FiringSFC myFiringSFC;
    private double myDefaultWaitTime = 0;
    private TrackingFirerDefinition myFireDef = new TrackingFirerDefinition();

    public TrackingFireSFC (IGame game, FiringSFC firingSubFormController) {
        myView = new TrackingFirerSFV(game.getAuthorshipData().getMyCreatedGroups());
        myFormData = myView.getData();
        myGame = game;
        myFiringSFC = firingSubFormController;
    }

    public TrackingFireSFC (IGame game, FiringSFVmult firingSFVmult) {
        myView = new TrackingFirerSFV(game.getAuthorshipData().getMyCreatedGroups());
        myFormData = myView.getData();
        myGame = game;
    }

    public TrackingFireSFC (IGame game, FiringSFCmult sFC) {
        myView = new TrackingFirerSFV(game.getAuthorshipData().getMyCreatedGroups());
        myFormData = myView.getData();
        myGame = game;
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultWaitTime);
    }

    private void populateViewsWithData (double wait) {
        myFormData.set(myView.getWaitTimeKey(), Double.toString(wait));
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        // myFiringSFC.removeCurrentFirer(item); TODO: fix this issue
        myFireDef.setGame(myGame);
        Double waitTime =
                Double.valueOf(myFormData.getValueProperty(myView.getWaitTimeKey()).get());
        myFireDef.setWaitTime(waitTime);
        myFireDef.setTargets(myView.getTargetsCoice().getSelected());
        myFireDef.setProjectileDefinition(myFiringSFC.getMyMissile());
        if (!item.getModuleDefinitions().contains(myFireDef)) {
            item.addModule(myFireDef);
        }
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
