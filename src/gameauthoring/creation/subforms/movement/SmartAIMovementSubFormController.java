package gameauthoring.creation.subforms.movement;

import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class SmartAIMovementSubFormController implements ISubFormControllerSprite {

    private SmartAIMovementSubFormView myView;
    private IFormDataManager myFormData;

    public SmartAIMovementSubFormController () {
        myView = new SmartAIMovementSubFormView();
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

}
