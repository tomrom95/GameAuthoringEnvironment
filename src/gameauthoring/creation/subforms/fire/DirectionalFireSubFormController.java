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

    private static Predicate<ModuleDefinition> findDirectionalFirer () {
        return p -> p.getClass().equals(new DirectionalFirerDefinition().getClass());
    }

    public DirectionalFireSubFormController (IGame game) {
        myView = new DirectionalFireSubFormView();
        myFormData = myView.getData();
        myGame = game;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        try {
            Object firingDefinitionObject =
                    item.getModuleDefinitions().stream().filter(findDirectionalFirer())
                            .toArray()[0];
            DirectionalFirerDefinition myDef = new DirectionalFirerDefinition();

            myDef = (DirectionalFirerDefinition) firingDefinitionObject;
            myFormData.set(myView.getMyAngleKey(), Double.toString(myDef.getAngle()));
            myFormData.set(myView.getMyWaitTimeKey(), Double.toString(myDef.getWaitTime()));
        }
        catch (ArrayIndexOutOfBoundsException e) {
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
        DirectionalFirerDefinition trackingFireDef = new DirectionalFirerDefinition();
        trackingFireDef.setGame(myGame);
        Double angle =
                Double.valueOf(myFormData.getValueProperty(myView.getMyAngleKey()).get()) *
                       Math.PI / 180; // tangent functions need radians
        trackingFireDef.setAngle(angle);
        Double waitTime =
                Double.valueOf(myFormData.getValueProperty(myView.getMyWaitTimeKey()).get());
        trackingFireDef.setWaitTime(waitTime);      
        item.addModule(trackingFireDef);

    }

}
