package gameauthoring.creation.subforms.movement;

import engine.definitions.ConstantMoverDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * constant movement modules
 * 
 * @author Dhrumil
 *
 */

public class ConstantMoverSubFormController implements ISubFormControllerSprite {

    private ConstantMoverSubFormView myView;
    private IFormDataManager myFormData;
    private double myDefaultXVel = 0;
    private double myDefaultYVel = 0;

    public ConstantMoverSubFormController () {
        myView = new ConstantMoverSubFormView();
        myFormData = myView.getData();
    }
    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultXVel, myDefaultYVel);
    }
    private void populateViewsWithData (double xVel, double yVel) {
        myFormData.set(myView.getMyXVelKey(),
                       Double.toString(xVel));
        myFormData.set(myView.getMyYVelKey(),
                       Double.toString(yVel));

    }
    @Override
    public void updateItem (SpriteDefinition item) {

        ConstantMoverDefinition myMovementDefinition = new ConstantMoverDefinition();

        Double xVelocity =
                Double.valueOf(myFormData.getValueProperty(myView.getMyXVelKey()).get());

        Double yVelocity =
                Double.valueOf(myFormData.getValueProperty(myView.getMyYVelKey()).get());
        myMovementDefinition.setXVel(xVelocity);
        myMovementDefinition.setYVel(yVelocity);

        item.setMovementDefinition(myMovementDefinition);

    }
   
    @Override
    public void populateViewsWithData (SpriteDefinition item) {

        ConstantMoverDefinition movementDefinition =
                (ConstantMoverDefinition) item.getMovementDefinition();

        myFormData.set(myView.getMyXVelKey(),
                       Double.toString(movementDefinition.getXVel()));
        myFormData.set(myView.getMyYVelKey(),
                       Double.toString(movementDefinition.getYVel()));

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    

}
