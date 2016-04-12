package gameauthoring.creation.subforms;

import engine.definitions.KeyControlDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.UserMoverDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;

public class UserMoverSubFormController implements ISubFormControllerSprite {

    private UserMoverSubFormView myView;
    private IFormDataManager myFormData;
    
   

    public UserMoverSubFormController () {
        this.myView = new UserMoverSubFormView();
        this.myFormData = myView.getData();
    }
   

    @Override
    public void updateItem (SpriteDefinition item) {
        
        UserMoverDefinition myUMD = new UserMoverDefinition();
        Double mySpeedDouble = Double.valueOf(myFormData.getValueProperty(myView.getSpeedKey()).get());
        myUMD.setSpeed(mySpeedDouble);
        
        KeyControlDefinition myKeyControlDef = new KeyControlDefinition();
        myKeyControlDef.setUp(myFormData.getValueProperty(myView.getUpKey()).get());
        myKeyControlDef.setDown(myFormData.getValueProperty(myView.getDownKey()).get());
        myKeyControlDef.setLeft(myFormData.getValueProperty(myView.getDownKey()).get());
        myKeyControlDef.setRight(myFormData.getValueProperty(myView.getLeftKey()).get());
        
        myUMD.setKeyControlDefintion(myKeyControlDef);
        
        item.setMovementDefinition(myUMD);
        
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        /*
         * change names of getters to MySpeedKey!!!
         * ask about casting, Ryan says this is a bad idea and the actually UserMoverDefinition is needed 
         */
        UserMoverDefinition thisUMD = (UserMoverDefinition) item.getMovementDefinition();
        myFormData.set(myView.getSpeedKey(), Double.toString(item.getMovementDefinition().getSpeed()));
        myFormData.set(myView.getUpKey(), thisUMD.getKeyControlDefintion().getUp());
        myFormData.set(myView.getDownKey(), thisUMD.getKeyControlDefintion().getDown());
        myFormData.set(myView.getLeftKey(), thisUMD.getKeyControlDefintion().getLeft());
        myFormData.set(myView.getRightKey(), thisUMD.getKeyControlDefintion().getRight());
        
         

    }

    @Override
    public ISubFormView getSubFormView () {
        // TODO Auto-generated method stub
        return myView;
    }

}
