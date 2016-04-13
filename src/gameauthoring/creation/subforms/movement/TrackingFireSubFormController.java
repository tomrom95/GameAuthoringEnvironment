package gameauthoring.creation.subforms.movement;

import engine.definitions.DirectionalFireDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * tracking movement modules and projectiles
 * 
 * @author Dhrumil
 *
 */

public class TrackingFireSubFormController implements ISubFormControllerSprite {

    private TrackingFireSubFormView myView;
    private IFormDataManager myFormData;

    public TrackingFireSubFormController () {
        myView = new TrackingFireSubFormView();
        myFormData = myView.getData();
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        TrackingFirerDefinition movementDefinition =
                (TrackingFirerDefinition) item;

        // myFormData.set(myView.getMyXVelKey(),
        // Double.toString(movementDefinition.getXVel()));
        // myFormData.set(myView.getMyYVelKey(),
        // Double.toString(movementDefinition.getYVel()));

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        // DirectionalFireDefinition movementDefinition = new DirectionalFireDefinition();
        // Double xVelocity =
        // Double.valueOf(myFormData.getValueProperty(myView.getMyXVelKey()).get());
        //
        // Double yVelocity =
        // Double.valueOf(myFormData.getValueProperty(myView.getMyYVelKey()).get());
        // myMovementDefinition.setXVel(xVelocity);
        // myMovementDefinition.setYVel(yVelocity);
        //
        // item.setMovementDefinition(myMovementDefinition);
    }

}