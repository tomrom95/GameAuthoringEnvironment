package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.List;
import engine.IGame;
import engine.definitions.DirectionalFirerDefinition;
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
 * @author Dhrumil
 *
 */

public class DirectionalFireSubFormController implements ISubFormControllerSprite {

    private DirectionalFireSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;

    public DirectionalFireSubFormController (IGame game) {
        myView = new DirectionalFireSubFormView();
        myFormData = myView.getData();
        myGame = game;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        // DirectionalFirerDefinition fireDefinition = (DirectionalFirerDefinition)item;
        // myFormData.set(myView.getWaitTime(),
        // Double.toString(item.getFireDefinition().getWaitTime));
        // myFormData.set(myView.getAngle(), Double.toString(item.getFireDefinition().getAngle());

        // myFormData.set(myView.getMyProjectileKey());

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        DirectionalFirerDefinition trackingFireDef = new DirectionalFirerDefinition();
        trackingFireDef.setGame(myGame);
        Double angle = Double.valueOf(myFormData.getValueProperty(myView.getMyAngleKey()).get());
        trackingFireDef.setAngle(angle);
        Double waitTime =
                Double.valueOf(myFormData.getValueProperty(myView.getMyWaitTimeKey()).get());
        trackingFireDef.setWaitTime(waitTime);
        String projectile = myFormData.getValueProperty(myView.getMyProjectileKey()).get();
        // trackingFireDef.setProjectileDefinition(new SpriteDefinition(projectile));
        item.addModule(trackingFireDef);

    }

}
