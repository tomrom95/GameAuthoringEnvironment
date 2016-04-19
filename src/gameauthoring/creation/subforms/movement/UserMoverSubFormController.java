package gameauthoring.creation.subforms.movement;

import engine.definitions.KeyControlDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.UserMoverDefinition;
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
public class UserMoverSubFormController implements ISubFormControllerSprite {

    private UserMoverSubFormView myView;
    private IFormDataManager myFormData;
    private double myDefaultSpeed = 0;
    private String myDefaultUpKey = "w";
    private String myDefaultDownKey = "s";
    private String myDefaultRightKey = "f";
    private String myDefaultLeftKey = "a";
    

    public UserMoverSubFormController () {
        this.myView = new UserMoverSubFormView();
        this.myFormData = myView.getData();
    }
    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultSpeed, myDefaultUpKey, myDefaultDownKey, myDefaultRightKey, myDefaultLeftKey);
    }
    private void populateViewsWithData (double speed, String up, String down, String left, String right) {
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
        myKeyControlDef.setLeft(myFormData.getValueProperty(myView.getDownKey()).get());
        myKeyControlDef.setRight(myFormData.getValueProperty(myView.getLeftKey()).get());

        myUMD.setKeyControlDefintion(myKeyControlDef);

        item.setMovementDefinition(myUMD);

    }

//    @Override
//    public void populateViewsWithData (SpriteDefinition item) {
//        UserMoverDefinition thisUMD = (UserMoverDefinition) item.getMovementDefinition();
//        myFormData.set(myView.getSpeedKey(),
//                       Double.toString(item.getMovementDefinition().getSpeed()));
//        myFormData.set(myView.getUpKey(), thisUMD.getKeyControlDefintion().getUp());
//        myFormData.set(myView.getDownKey(), thisUMD.getKeyControlDefintion().getDown());
//        myFormData.set(myView.getLeftKey(), thisUMD.getKeyControlDefintion().getLeft());
//        myFormData.set(myView.getRightKey(), thisUMD.getKeyControlDefintion().getRight());
//
//    }

    @Override
    public ISubFormView getSubFormView () {
        // TODO Auto-generated method stub
        return myView;
    }
   

}
