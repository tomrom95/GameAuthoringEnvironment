package gameauthoring.creation.subforms.movement;

import engine.definitions.concrete.KeyControlDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.UserMoverDefinition;
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
    private double myDefaultSpeed;
    private String myDefaultUpKey = "W";
    private String myDefaultDownKey = "S";
    private String myDefaultLeftKey = "A";
    private String myDefaultRightKey = "D";

    public UserMoverSFC () {
        myView = new UserMoverSFV();
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
        myView.populateWithData(speed, up, down, left, right);

    }

    @Override
    public void updateItem (SpriteDefinition item) {
        UserMoverDefinition myUMD = new UserMoverDefinition();
        double mySpeedDouble = myView.getSpeed();
        myUMD.setSpeed(mySpeedDouble);
        KeyControlDefinition myKeyControlDef = new KeyControlDefinition();
        myKeyControlDef.setUp(myView.getUpKey());
        myKeyControlDef.setDown(myView.getDownKey());
        myKeyControlDef.setLeft(myView.getLeftKey());
        myKeyControlDef.setRight(myView.getRightKey());
        myUMD.setKeyControlDefintion(myKeyControlDef);
        item.setMovementDefinition(myUMD);

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        UserMoverDefinition movDef =
                (UserMoverDefinition) item.getMovementDefinition();
        KeyControlDefinition keyControlDef = movDef.getKeyControlDefintion();
        myView.populateWithData(movDef.getSpeed(), keyControlDef.getUp(), keyControlDef.getDown(),
                                keyControlDef.getLeft(), keyControlDef.getRight());
    }

}
