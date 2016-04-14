package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import engine.IGame;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.ModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.sprite.SpriteType;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * linear, directional movement modules
 * 
 * @author Dhrumil Timko
 *
 */

public class DirectionalFireSubFormController implements ISubFormControllerSprite {

    private DirectionalFireSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private FiringSubFormController myFiringSFC;

    private static Predicate<ModuleDefinition> findDirectionalFirer () {

        return p -> p.getClass().equals(new DirectionalFirerDefinition().getClass());
    }

    public DirectionalFireSubFormController (IGame game) {
        myView = new DirectionalFireSubFormView();
        myFormData = myView.getData();
        myGame = game;
    }

    public DirectionalFireSubFormController (IGame game,
                                             FiringSubFormController firingSubFormController) {
        myView = new DirectionalFireSubFormView();
        myFormData = myView.getData();
        myGame = game;
        myFiringSFC = firingSubFormController;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {

        // TODO: add default populate method for new object?
        if (item.getModuleDefinitions().isEmpty())
            return;

        try {
            Object firingDefinitionObject =
                    item.getModuleDefinitions().stream().filter(findDirectionalFirer())
                            .toArray()[0];
            DirectionalFirerDefinition myDef = new DirectionalFirerDefinition();
            if (firingDefinitionObject.getClass().equals(myDef.getClass())) {
                myDef = (DirectionalFirerDefinition) firingDefinitionObject;
            }
            else {
                myDef = null;
                /*
                 * throw exception here?
                 */
            }
            myFormData.set(myView.getMyAngleKey(),
                           Double.toString(myDef.getAngle() * 180 / Math.PI));
            myFormData.set(myView.getMyWaitTimeKey(), Double.toString(myDef.getWaitTime()));
            // myFormData.set(myView.getMyProjectileKey(), myDef.getProjectileDefinition());
        }
        catch (Exception e) {
            myFormData.set(myView.getMyAngleKey(), "");
            myFormData.set(myView.getMyProjectileKey(), "");
        }

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
