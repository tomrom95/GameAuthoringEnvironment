package gameauthoring.creation.subforms.movement;

import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ConstantMoverDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * constant movement modules
 * 
 * @author Joe Lilien
 * @author Dhrumil
 *
 */

public class ConstantMoverSFC implements ISubFormControllerSprite {

    private IConstantMoverSFV myView;
    private IFormDataManager myFormData;
    private double myDefaultSpeed = 0;
    private double myDefaultOrientation = 0;

    public ConstantMoverSFC () {
        myView = new ConstantMoverSFV();
        myFormData = myView.getData();
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultSpeed, myDefaultOrientation);
    }

    private void populateViewsWithData (double speed, double orientation) {
        myFormData.set(myView.getMySpeedKey(),
                       Double.toString(speed));
        myFormData.set(myView.getMyOrientationKey(),
                       Double.toString(orientation));
        

    }

    @Override
    public void updateItem (SpriteDefinition item) {
        ConstantMoverDefinition myMovementDefinition = new ConstantMoverDefinition();
        Double speed =
                Double.valueOf(myFormData.getValueProperty(myView.getMySpeedKey()).get());

        Double orientation =
                Double.valueOf(myFormData.getValueProperty(myView.getMyOrientationKey()).get());
        myMovementDefinition.setSpeed(speed);
        myMovementDefinition.setOrientaiton(orientation);
        item.setMovementDefinition(myMovementDefinition);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        ConstantMoverDefinition movementDefinition =
                (ConstantMoverDefinition) item.getMovementDefinition();

        myFormData.set(myView.getMySpeedKey(),
                       Double.toString(movementDefinition.getSpeed()));
        myFormData.set(myView.getMyOrientationKey(),
                       Double.toString(movementDefinition.getOrientation()));
        
    }

}
