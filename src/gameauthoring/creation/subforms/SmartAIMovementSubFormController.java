package gameauthoring.creation.subforms;

import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;

public class SmartAIMovementSubFormController implements ISubFormControllerSprite {
    
    private SmartAIMovementSubFormView myView;
    private IFormDataManager myFormData;
   
    public SmartAIMovementSubFormController() {
        myView = new SmartAIMovementSubFormView();
        this.myFormData = myView.getData();

    }
    
    @Override
    public void updateItem (SpriteDefinition item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        // TODO Auto-generated method stub

    }

    @Override
    public ISubFormView getSubFormView () {
        // TODO Auto-generated method stub
        return myView;
    }

}
