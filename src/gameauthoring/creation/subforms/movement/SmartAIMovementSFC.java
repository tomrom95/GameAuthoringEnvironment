package gameauthoring.creation.subforms.movement;

import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class SmartAIMovementSFC implements ISubFormControllerSprite {

    private SmartAIMovementSFV myView;
    private double myDefaultSpeed = 0;

    public SmartAIMovementSFC () {
        myView = new SmartAIMovementSFV();
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
        myView.populateWithData(myDefaultSpeed);

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        // TODO Auto-generated method stub
        
    }

}
