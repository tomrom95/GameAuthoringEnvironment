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
 * @author Dhrumil Timko Schreck Lilien
 *
 */

public class TrackingFireSFC implements RemovableSpriteSFC {

    private TrackingFirerSFV myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private FiringSFCmult myFiringSFC;
    private double myDefaultWaitTime = 0;
    private TrackingFirerDefinition myFireDef = new TrackingFirerDefinition();

//    public TrackingFireSFC (IGame game, FiringSFC firingSubFormController) {
//        myView = new TrackingFirerSFV(game.getAuthorshipData().getMyCreatedGroups());
//        myFormData = myView.getData();
//        myGame = game;
//        myFiringSFC = firingSubFormController;
//    }

    public TrackingFireSFC (IGame game, FiringSFCmult sfc) {
        myView = new TrackingFirerSFV(game.getAuthorshipData(),e->sfc.removeSFC(this));
        myFormData = myView.getData();
        myGame = game;
        myFiringSFC = sfc;
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
        myFireDef.setGame(myGame);
        Double waitTime =
                Double.valueOf(myFormData.getValueProperty(myView.getWaitTimeKey()).get());
        myFireDef.setWaitTime(waitTime);
        myFireDef.setTargets(myView.getTargetsCoice().getSelected());
        myFireDef.setProjectileDefinition(myView.getSelectedMissile());
        if (!item.getModuleDefinitions().contains(myFireDef)) {
            item.addModule(myFireDef);
        }
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void removeModule (SpriteDefinition item) {
        if(item.getModuleDefinitions().contains(myFireDef)){
            item.remove(myFireDef);       
        }
        myFiringSFC.removeSFC(this);        
    }

}
