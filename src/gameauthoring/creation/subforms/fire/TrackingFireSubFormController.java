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

public class TrackingFireSubFormController implements ISubFormControllerSprite {

    private TrackingFireSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private FiringSubFormController myFiringSFC;
    private double myDefaultWaitTime = 0;

    public TrackingFireSubFormController (IGame game,
                                          FiringSubFormController firingSubFormController) {
        myView = new TrackingFireSubFormView(game.getAuthorshipData().getMyCreatedGroups());
        myFormData = myView.getData();
        myGame = game;
        myFiringSFC = firingSubFormController;
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
        TrackingFirerDefinition trackingFireDef = new TrackingFirerDefinition();
        trackingFireDef.setGame(myGame);
        Double waitTime =
                Double.valueOf(myFormData.getValueProperty(myView.getWaitTimeKey()).get());
        trackingFireDef.setWaitTime(waitTime);
        trackingFireDef.setTargets(myView.getTargetsCoice().getSelected());
        trackingFireDef.setProjectileDefinition(myFiringSFC.getMyMissile());

        item.addModule(trackingFireDef);

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
