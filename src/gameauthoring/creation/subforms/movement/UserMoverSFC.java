package gameauthoring.creation.subforms.movement;

import engine.definitions.concrete.KeyControlDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ConstantMoverDefinition;
import engine.definitions.moduledef.UserMoverDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving user
 * movement modules
 * 
 * @author Dhrumil Timko
 *
 */
public class UserMoverSFC implements ISubFormControllerSprite {

    private UserMoverSFV myView;
    private IFormDataManager myFormData;
    private double myDefaultSpeed = 0;
    private String myDefaultUpKey = "W";
    private String myDefaultDownKey = "S";
    private String myDefaultLeftKey = "A";
    private String myDefaultRightKey = "D";

    public UserMoverSFC () {
        this.myView = new UserMoverSFV();
        this.myFormData = myView.getData();
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultSpeed, myDefaultUpKey, myDefaultDownKey,
                              myDefaultLeftKey, myDefaultRightKey);
    }

    private void populateViewsWithData (double speed,
                                        String up,
                                        String down,
                                        String left,
                                        String right) {
        myFormData.set(myView.getSpeedKey(),
                       Double.toString(speed));
        myFormData.set(myView.getUpKey(), up);
        myFormData.set(myView.getDownKey(), down);
        myFormData.set(myView.getLeftKey(), left);
        myFormData.set(myView.getRightKey(), right);

    }

    @Override
    public void updateItem (SpriteDefinition item) {

        UserMoverDefinition myUMD = new UserMoverDefinition();
        Double mySpeedDouble =
                Double.valueOf(myFormData.getValueProperty(myView.getSpeedKey()).get());
        myUMD.setSpeed(mySpeedDouble);

        KeyControlDefinition myKeyControlDef = new KeyControlDefinition();
        myKeyControlDef.setUp(myFormData.getValueProperty(myView.getUpKey()).get());
        myKeyControlDef.setDown(myFormData.getValueProperty(myView.getDownKey()).get());
        myKeyControlDef.setLeft(myFormData.getValueProperty(myView.getLeftKey()).get());
        myKeyControlDef.setRight(myFormData.getValueProperty(myView.getRightKey()).get());

        myUMD.setKeyControlDefintion(myKeyControlDef);

        item.setMovementDefinition(myUMD);

    }

    @Override
    public ISubFormView getSubFormView () {
        // TODO Auto-generated method stub
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        UserMoverDefinition movDef =
                (UserMoverDefinition) item.getMovementDefinition();
        KeyControlDefinition keyControlDef = movDef.getKeyControlDefintion();
        
        populateViewsWithData(movDef.getSpeed(), keyControlDef.getUp(), keyControlDef.getDown(), keyControlDef.getLeft(), keyControlDef.getRight());
        
    }

}
