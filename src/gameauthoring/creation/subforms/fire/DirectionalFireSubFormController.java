package gameauthoring.creation.subforms.fire;

import engine.IGame;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * linear, directional movement modules
 * 
 * @author Dhrumil Timko Schreck
 *
 */

public class DirectionalFireSubFormController implements ISubFormControllerSprite {

    private DirectionalFireSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private FiringSubFormController myFiringSFC;
    private double myDefaultAngle = 0;
    private double myDefaultWaitTime = 0;

    public DirectionalFireSubFormController (IGame game,
                                             FiringSubFormController firingSubFormController) {
        myView = new DirectionalFireSubFormView();
        myFormData = myView.getData();
        myGame = game;
        myFiringSFC = firingSubFormController;
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultAngle, myDefaultWaitTime);
    }

    private void populateViewsWithData (double angle, double wait) {
        myFormData.set(myView.getMyAngleKey(), Double.toString(angle));
        myFormData.set(myView.getMyWaitTimeKey(), Double.toString(wait));
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        DirectionalFirerDefinition directionalFireDef = new DirectionalFirerDefinition();
        directionalFireDef.setGame(myGame);
        Double angle =
                Double.valueOf(myFormData.getValueProperty(myView.getMyAngleKey()).get()) *
                       Math.PI / 180; // tangent functions need radians
        directionalFireDef.setAngle(angle);
        Double waitTime =
                Double.valueOf(myFormData.getValueProperty(myView.getMyWaitTimeKey()).get());
        directionalFireDef.setWaitTime(waitTime);

        directionalFireDef.setProjectileDefinition(myFiringSFC.getMyMissile());
        item.addModule(directionalFireDef);

    }

}
