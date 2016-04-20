package gameauthoring.creation.subforms.fire;

import engine.IGame;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.FirerDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.ErrorMessage;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * linear, directional movement modules
 * 
 * @author Dhrumil Timko Schreck Lilien
 *
 */

public class DirectionalFireSFC implements ISubFormControllerSprite {

    private DirectionalFireSFV myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private FiringSubFormController myFiringSFC;
    private double myDefaultAngle = 0;
    private double myDefaultWaitTime = 0;
    private DirectionalFirerDefinition myFireDef = new DirectionalFirerDefinition();

    public DirectionalFireSFC (IGame game, FiringSubFormController firingSubFormController) {
        myView = new DirectionalFireSFV();
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

    // TODO: Check whether input is empty string or not, it gives error for now
    @Override
    public void updateItem (SpriteDefinition item) {
        //myFiringSFC.removeCurrentFirer(item); TODO: fix this issue
        try {
            Double angle =
                    Double.valueOf(myFormData.getValueProperty(myView.getMyAngleKey()).get()) *
                           Math.PI / 180; // tangent functions need radians
            Double waitTime =
                    Double.valueOf(myFormData.getValueProperty(myView.getMyWaitTimeKey()).get());
            myFireDef.setGame(myGame);
            myFireDef.setAngle(angle);
            myFireDef.setWaitTime(waitTime);
            myFireDef.setProjectileDefinition(myFiringSFC.getMyMissile());    
            if(!item.getModuleDefinitions().contains(myFireDef)){
                item.addModule(myFireDef);
            }
        }
        catch (Exception e) {
            ErrorMessage err =
                    new ErrorMessage("All Fields for Directional Firer Must Be Complete");
            err.showError();
        }
    }
    
    

}
