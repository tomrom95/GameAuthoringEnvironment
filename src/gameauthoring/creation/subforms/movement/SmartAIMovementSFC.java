package gameauthoring.creation.subforms.movement;

import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class SmartAIMovementSFC implements ISubFormControllerSprite {

    private SmartAIMovementSFV myView;
    private IFormDataManager myFormData;

    public SmartAIMovementSFC () {
        myView = new SmartAIMovementSFV();
        this.myFormData = myView.getData();

    }

    @Override
    public void updateItem (SpriteDefinition item) {
        // TODO Auto-generated method stub

    }

    @Override
    public ISubFormView getSubFormView () {
        // TODO Auto-generated method stub
        return myView;
    }

    @Override
    public void initializeFields () {
        // TODO Auto-generated method stub

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        // TODO Auto-generated method stub
        
    }

}
